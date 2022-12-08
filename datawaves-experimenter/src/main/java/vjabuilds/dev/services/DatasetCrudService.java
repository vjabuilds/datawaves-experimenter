package vjabuilds.dev.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hibernate.reactive.mutiny.Mutiny;

import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import vjabuilds.dev.repos.DatasetRepo;
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
        var original = repo.findById(id);
        Uni<List<DatasetListModel>> children = original.chain(x -> Mutiny.fetch(x.getChildrenDatasets()))
            .onItem().ifNotNull().transform(x -> 
                x.stream().map(t -> {
                    return new DatasetListModel(
                        t.getDatasetId(), 
                        t.getName(), 
                        t.getSource(), 
                        t.getDescription(), 
                        t.getType());
            }).toList());
        Uni<DatasetListModel> parentDs = original.chain(x -> Mutiny.fetch(x.getParentDataset()))
            .onItem().ifNotNull().transform(x -> new DatasetListModel(
                x.getDatasetId(), 
                x.getName(),
                x.getSource(),
                x.getDescription(),
                x.getType())
            );
        Uni<PipelineListModel> parentPipeline = original.chain(x -> Mutiny.fetch(x.getParentPipeline()))
            .onItem().ifNotNull().transform(x -> new PipelineListModel(
                x.getPipelineId(), 
                x.getName(), 
                x.getVersion(), 
                x.getDescription(), 
                x.getYamlFormat()
            ));

        return Uni.combine().all().unis(original, children, parentDs, parentPipeline)
            .asTuple().map(x -> new DatasetDetailsModel(
                    x.getItem1().getDatasetId(), 
                    x.getItem1().getName(),
                    x.getItem1().getSource(),
                    x.getItem1().getDescription(),
                    x.getItem1().getType(),  
                    x.getItem3(),
                    x.getItem4(),
                    x.getItem2()
               )
            );
    }
}
