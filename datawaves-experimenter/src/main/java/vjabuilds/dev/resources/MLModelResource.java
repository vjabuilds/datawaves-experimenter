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
import vjabuilds.dev.services.MLModelCrudService;
import vjabuilds.dev.services.mlmodel_crud_models.MLModelCreateModel;

@Path("/mlmodel")
@AllArgsConstructor
public class MLModelResource {
    @Inject MLModelCrudService modelService;

    @GET
    public Uni<Response> getDatasets()
    {
        return modelService.getMLModels().map(x -> 
            Response.ok(x).build());
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getDatasetDetails(@RestPath Long id)
    {
        return modelService.getMLModelDetails(id)
            .onItem().ifNotNull().transform(x -> Response.ok(x).build())
            .onItem().ifNull().continueWith(() -> Response.status(404).build());

    }

    @POST
    public Uni<Response> createDataset(MLModelCreateModel model) 
    {
        return modelService.createMLModel(model)
            .onItem().ifNotNull().transform(x -> Response.ok(x).build())
            .onItem().ifNull().continueWith(Response.status(400).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteDataset(@RestPath Long id)
    {
        return modelService.deleteMLModel(id)
            .onItem().ifNotNull().transform(x -> Response.ok().build())
            .onItem().ifNull().continueWith(() -> Response.status(404).build());
    }
}
