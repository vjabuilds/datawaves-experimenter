package vjabuilds.dev.resources;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.reactive.RestPath;

import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import vjabuilds.dev.services.DatasetCrudService;
import vjabuilds.dev.services.dataset_crud_models.DatasetCreateModel;

@Path("/datasets")
@AllArgsConstructor
public class DatasetResource {

    @Inject DatasetCrudService datasetService;

    @GET
    @RolesAllowed({"admin"})
    public Uni<Response> getDatasets()
    {
        return datasetService.getDatasets().map(x -> 
            Response.ok(x).build());
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public Uni<Response> getDatasetDetails(@RestPath Long id)
    {
        return datasetService.getDatasetDetails(id)
            .onItem().ifNotNull().transform(x -> Response.ok(x).build())
            .onItem().ifNull().continueWith(() -> Response.status(404).build());

    }

    @POST
    public Uni<Response> createDataset(DatasetCreateModel ds) 
    {
        return datasetService.createDataset(ds)
            .onItem().ifNotNull().transform(x -> Response.ok(x).build())
            .onItem().ifNull().continueWith(Response.status(400).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteDataset(@RestPath Long id)
    {
        return datasetService.deleteDataset(id)
            .onItem().ifNotNull().transform(x -> Response.ok().build())
            .onItem().ifNull().continueWith(() -> Response.status(404).build());
    }
}
