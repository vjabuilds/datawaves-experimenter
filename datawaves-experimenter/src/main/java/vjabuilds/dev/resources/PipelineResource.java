package vjabuilds.dev.resources;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.reactive.RestPath;

import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import vjabuilds.dev.services.PipelineCrudService;
import vjabuilds.dev.services.pipeline_crud_models.PipelineCreateModel;

@Path("/pipelines")
@AllArgsConstructor
@PermitAll
public class PipelineResource {
    @Inject PipelineCrudService pipelineService;

    @GET
    public Uni<Response> getPipelines()
    {
        return pipelineService.getPipelines()
            .map(x -> Response.ok().entity(x).build());
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getPipeline(@RestPath Long id)
    {
        return pipelineService.getPipelineDetails(id)
            .map(x -> Response.ok().entity(x).build());
    }

    @POST
    public Uni<Response> createPipeline(PipelineCreateModel model)
    {
        return pipelineService.createPipeline(model)
            .map(x -> Response.ok().entity(x).build());
    }

}
