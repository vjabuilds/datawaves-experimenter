package vjabuilds.dev.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import vjabuilds.dev.models.Dataset;
import vjabuilds.dev.models.Experiment;
import vjabuilds.dev.models.MLModel;
import vjabuilds.dev.repos.ExperimentRepo;
import vjabuilds.dev.services.experiment_crud_models.ExperimentCreateModel;
import vjabuilds.dev.services.experiment_crud_models.ExperimentDetailsModel;
import vjabuilds.dev.services.experiment_crud_models.ExperimentListModel;
import vjabuilds.dev.services.mlmodel_crud_models.MLModelDetailsModel;

@ApplicationScoped
public class ExperimentCrudService {
    @Inject DatasetCrudService datasetService;
    @Inject MLModelCrudService modelService;
    @Inject ExperimentRepo expRepo;

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
            return Uni.combine().all().unis(original, model, dataset).asTuple()
                .map(x -> 
                    new ExperimentDetailsModel(
                        x.getItem1().getExperimentId(), 
                        x.getItem1().getName(), 
                        x.getItem1().getDescription(), 
                        x.getItem3(), 
                        x.getItem2())
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
}
