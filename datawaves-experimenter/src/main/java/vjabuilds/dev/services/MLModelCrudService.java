package vjabuilds.dev.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import vjabuilds.dev.models.MLModel;
import vjabuilds.dev.repos.MLModelRepo;
import vjabuilds.dev.services.mlmodel_crud_models.MLModelCreateModel;
import vjabuilds.dev.services.mlmodel_crud_models.MLModelDetailsModel;
import vjabuilds.dev.services.mlmodel_crud_models.MLModelListModel;

@ApplicationScoped
@AllArgsConstructor
public class MLModelCrudService {
    @Inject MLModelRepo repo;

    public Uni<List<MLModelListModel>> getDatasets()
    {
        return repo.listAll().map(x -> 
                    x.stream()
                    .map(mlm -> new MLModelListModel(mlm))
                    .collect(Collectors.toList()));
    }

    public Uni<MLModelDetailsModel> getDatasetDetails(Long id)
    {
        return Panache.withTransaction(() -> {
            var original = repo.findById(id);

            return original.map(x -> {
                    if(x == null)
                        return null;
                    return new MLModelDetailsModel(
                        x.getMlModelId(), 
                        x.getTaskType(), 
                        x.getDatasetType(), 
                        x.getName(), 
                        x.getDescription(), 
                        x.getYamlFormat());
                }
                );
        });
    }

    public Uni<MLModelDetailsModel> createDataset(MLModelCreateModel model)
    {
        return Panache.withTransaction(() -> repo.persist(new MLModel(
            null, 
            model.taskType(), 
            model.datasetType(), 
            model.name(), 
            model.description(), 
            model.yamlFormat(), 
            null
        ))).flatMap(x -> getDatasetDetails(x.getMlModelId()));
    }

    public Uni<MLModel> deleteDataset(Long id)
    {
        return Panache.withTransaction(() -> 
            repo.findById(id).onItem().ifNotNull().invoke(x -> x.setDeleted(ZonedDateTime.now()))
        );
    }
}
