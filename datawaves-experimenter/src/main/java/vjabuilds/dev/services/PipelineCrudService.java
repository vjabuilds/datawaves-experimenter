package vjabuilds.dev.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hibernate.reactive.mutiny.Mutiny;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import vjabuilds.dev.models.Pipeline;
import vjabuilds.dev.repos.PipelineRepo;
import vjabuilds.dev.services.dataset_crud_models.DatasetListModel;
import vjabuilds.dev.services.pipeline_crud_models.PipelineCreateModel;
import vjabuilds.dev.services.pipeline_crud_models.PipelineDetailsModel;
import vjabuilds.dev.services.pipeline_crud_models.PipelineListModel;

@AllArgsConstructor
@ApplicationScoped
public class PipelineCrudService {
    @Inject PipelineRepo repo;
    
    public Uni<List<PipelineListModel>> getPipelines()
    {
        return repo.listAll().map(x -> 
                    x.stream()
                    .map(p -> new PipelineListModel(p))
                    .collect(Collectors.toList()));
    }

    public Uni<PipelineDetailsModel> getPipelineDetails(Long id)
    {
        return repo.findById(id).chain(x -> 
            Mutiny.fetch(x.getChildDatasets()).map(
                ds -> new PipelineDetailsModel(
                    x.getPipelineId(),
                    x.getDescription(),
                    x.getName(), 
                    x.getVersion(),
                    x.getYamlFormat(),
                    ds != null ? ds.stream().map(d -> new DatasetListModel(d)).collect(Collectors.toList()) 
                               : List.of()
                    )
            ));
    }

    public Uni<PipelineDetailsModel> createPipeline(PipelineCreateModel model)
    {
        return Panache.withTransaction(() -> repo.persist(new Pipeline(
            null, model.description(), model.name(), model.version(), model.yamlFormat(), null
        ))).flatMap(x -> getPipelineDetails(x.getPipelineId()));
    }
}
