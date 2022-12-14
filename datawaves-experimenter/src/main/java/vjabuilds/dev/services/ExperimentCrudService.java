package vjabuilds.dev.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hibernate.reactive.mutiny.Mutiny;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import vjabuilds.dev.models.Artifact;
import vjabuilds.dev.models.Dataset;
import vjabuilds.dev.models.Experiment;
import vjabuilds.dev.models.MLModel;
import vjabuilds.dev.repos.ArtifactRepo;
import vjabuilds.dev.repos.ExperimentRepo;
import vjabuilds.dev.services.experiment_crud_models.ArtifactCreateModel;
import vjabuilds.dev.services.experiment_crud_models.ArtifactListModel;
import vjabuilds.dev.services.experiment_crud_models.ExperimentCreateModel;
import vjabuilds.dev.services.experiment_crud_models.ExperimentDetailsModel;
import vjabuilds.dev.services.experiment_crud_models.ExperimentListModel;
import vjabuilds.dev.services.mlmodel_crud_models.MLModelDetailsModel;

@ApplicationScoped
public class ExperimentCrudService {
    @Inject DatasetCrudService datasetService;
    @Inject MLModelCrudService modelService;
    @Inject ExperimentRepo expRepo;
    @Inject ArtifactRepo artRepo;

    public Uni<List<ExperimentListModel>> getExperiments()
    {
        return expRepo.listAll().map(x -> 
                    x.stream()
                    .map(exp -> new ExperimentListModel(
                        exp.getExperimentId(),
                        exp.getName(),
                        exp.getDescription(),
                        exp.getDataset().getName(),
                        exp.getDataset().getDatasetId(),
                        exp.getModel().getName(),
                        exp.getModel().getMlModelId()
                    ))
                    .collect(Collectors.toList()));
    }

    public Uni<ExperimentDetailsModel> getExperimentDetails(Long id)
    {
        return Panache.withTransaction(() -> {
            var original = expRepo.findById(id);
            Uni<MLModelDetailsModel> model = original.flatMap(x -> modelService.getMLModelDetails(x.getModel().getMlModelId()));
            var dataset = original.flatMap(x -> datasetService.getDatasetDetails(x.getDataset().getDatasetId()));
            var artifacts = original.flatMap(x -> Mutiny.fetch(x.getArtifacts())).onItem().ifNotNull().transform(x ->
                x.stream().map(art -> new ArtifactListModel(art)).toList());
            return Uni.combine().all().unis(original, model, dataset, artifacts).asTuple()
                .map(x -> 
                    new ExperimentDetailsModel(
                        x.getItem1().getExperimentId(), 
                        x.getItem1().getName(), 
                        x.getItem1().getDescription(), 
                        x.getItem3(), 
                        x.getItem2(),
                        x.getItem4())
                );
        });
    }

    public Uni<ExperimentDetailsModel> createExperiment(ExperimentCreateModel model)
    {
        var mlModel = new MLModel();
        var dataset = new Dataset();
        mlModel.setMlModelId(model.modelId());
        dataset.setDatasetId(model.datasetId());
        var exp = new Experiment(
            null, model.name(), model.description(), 
            mlModel,
            dataset, 
            null
        );
        return Panache.withTransaction(() -> expRepo.persist(exp))
            .flatMap(x -> getExperimentDetails(x.getExperimentId()));
    }

    public Uni<Experiment> deleteExperiment(Long id)
    {
        return Panache.withTransaction(() -> 
            expRepo.findById(id).onItem().ifNotNull().invoke(x -> x.setDeleted(ZonedDateTime.now()))
        );
    }

    public Uni<ArtifactListModel> createArtifact(ArtifactCreateModel model)
    {
        Experiment exp = new Experiment();
        exp.setExperimentId(model.experimentId());
        Artifact art = new Artifact(null, model.name(), model.path(), model.description(), model.type(), exp);
        return Panache.withTransaction(() ->
            artRepo.persist(art).map(x -> new ArtifactListModel(x.getArtifactId(), 
                x.getName(), 
                x.getPath(), 
                x.getDescription(), 
                x.getType()))
        );
    }
}
