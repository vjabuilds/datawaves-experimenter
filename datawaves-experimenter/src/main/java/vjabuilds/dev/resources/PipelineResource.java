package vjabuilds.dev.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import vjabuilds.dev.models.Pipeline;
import vjabuilds.dev.repos.PipelineRepo;

@Path("/pipelines")
@AllArgsConstructor
@PermitAll
public class PipelineResource {
    @Inject PipelineRepo pipelineRepo;

    public record PipelinesVM(String name, String version, String description, String yamlFormat)
    {

    }

    @GET
    public Uni<Response> getPipelines()
    {
        return pipelineRepo.listAll().map(x -> 
            Response.ok()
                .entity(
                    x.stream()
                    .map(p -> new PipelinesVM(p.getName(), 
                                              p.getVersion(), 
                                              p.getDescription(), 
                                              p.getYamlFormat()))
                    .collect(Collectors.toList())).build()
        );
    }

    @POST
    public Uni<Response> createPipeline(Pipeline model)
    {
        return Panache.withTransaction(() -> pipelineRepo.persist(model))
            .onItem().transform(x -> Response.ok().status(Status.CREATED).entity(model).build())
            .onFailure().recoverWithItem(x -> Response.status(400).entity(x.toString()).build());

    }

}
