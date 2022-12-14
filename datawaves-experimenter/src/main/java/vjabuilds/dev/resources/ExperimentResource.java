package vjabuilds.dev.resources;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.reactive.RestPath;

import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import vjabuilds.dev.services.ExperimentCrudService;
import vjabuilds.dev.services.experiment_crud_models.ExperimentCreateModel;

@Path("/experiments")
@AllArgsConstructor
public class ExperimentResource {
    @Inject ExperimentCrudService experimentService;

    @GET
    public Uni<Response> getExperiments()
    {
        return experimentService.getExperiments().map(x -> 
            Response.ok(x).build());
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getExperimentDetails(@RestPath Long id)
    {
        return experimentService.getExperimentDetails(id)
            .onItem().ifNotNull().transform(x -> Response.ok(x).build())
            .onItem().ifNull().continueWith(() -> Response.status(404).build());

    }

    @POST
    public Uni<Response> createExperiment(ExperimentCreateModel exp) 
    {
        return experimentService.createExperiment(exp)
            .onItem().ifNotNull().transform(x -> Response.ok(x).build())
            .onItem().ifNull().continueWith(Response.status(400).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteExperiment(@RestPath Long id)
    {
        return experimentService.deleteExperiment(id)
            .onItem().ifNotNull().transform(x -> Response.ok().build())
            .onItem().ifNull().continueWith(() -> Response.status(404).build());
    }
}
