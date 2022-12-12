package vjabuilds.dev.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hibernate.reactive.mutiny.Mutiny;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import vjabuilds.dev.models.Dataset;
import vjabuilds.dev.repos.DatasetRepo;
import vjabuilds.dev.services.dataset_crud_models.DatasetCreateModel;
import vjabuilds.dev.services.dataset_crud_models.DatasetDetailsModel;
import vjabuilds.dev.services.dataset_crud_models.DatasetListModel;
import vjabuilds.dev.services.pipeline_crud_models.PipelineListModel;

@ApplicationScoped
@AllArgsConstructor
public class DatasetCrudService {
    @Inject DatasetRepo repo;

    public Uni<List<DatasetListModel>> getDatasets()
    {
        return repo.listAll().map(x -> 
                    x.stream()
                    .map(ds -> new DatasetListModel(
                                              ds.getDatasetId(),
                                              ds.getName(), 
                                              ds.getSource(), 
                                              ds.getDescription(), 
                                              ds.getType()))
                    .collect(Collectors.toList()));
    }

    public Uni<DatasetDetailsModel> getDatasetDetails(Long id)
    {
        return Panache.withTransaction(() -> {
            var original = repo.findById(id);
            Uni<List<DatasetListModel>> children = original.onItem().ifNotNull()
                .transformToUni(x -> Mutiny.fetch(x.getChildrenDatasets()))
                .onItem().ifNotNull().transform(x -> 
                    x.stream().map(t -> {
                        return new DatasetListModel(t);
                }).toList());
                
            Uni<DatasetListModel> parentDs = original.onItem().ifNotNull()
            .transformToUni(x -> Mutiny.fetch(x.getParentDataset()))
                .onItem().ifNotNull().transform(x -> new DatasetListModel(x));
            Uni<PipelineListModel> parentPipeline = original.onItem().ifNotNull()
            .transformToUni(x -> Mutiny.fetch(x.getParentPipeline()))
                .onItem().ifNotNull().transform(x -> new PipelineListModel(x));

            return Uni.combine().all().unis(original, children, parentDs, parentPipeline)
                .asTuple().map(x -> {
                    if(x.getItem1() == null)
                        return null;
                    return new DatasetDetailsModel(
                        x.getItem1().getDatasetId(), 
                        x.getItem1().getName(),
                        x.getItem1().getSource(),
                        x.getItem1().getDescription(),
                        x.getItem1().getType(),  
                        x.getItem3(),
                        x.getItem4(),
                        x.getItem2());
                }
                );
        });
    }

    public Uni<DatasetDetailsModel> createDataset(DatasetCreateModel model)
    {
        return Panache.withTransaction(() -> repo.persist(new Dataset(null, 
            model.name(), 
            model.source(), 
            model.description(), 
            model.type(), 
            null, 
            null, 
            null))).flatMap(x -> getDatasetDetails(x.getDatasetId()));
    }

    public Uni<Dataset> deleteDataset(Long id)
    {
        return Panache.withTransaction(() -> 
            repo.findById(id).onItem().ifNotNull().invoke(x -> x.setDeleted(ZonedDateTime.now()))
        );
    }
}
