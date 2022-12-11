package vjabuilds.dev.resources;

import java.time.ZonedDateTime;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.reactive.RestPath;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import vjabuilds.dev.models.Dataset;
import vjabuilds.dev.repos.DatasetRepo;
import vjabuilds.dev.services.DatasetCrudService;

@Path("/datasets")
@AllArgsConstructor
public class DatasetResource {

    @Inject DatasetRepo datasetRepo;
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
    public Uni<Response> createDataset(Dataset ds) 
    {
        return Panache.withTransaction(() -> {
            return datasetRepo.persist(ds).map(x -> Response.status(Status.CREATED).entity(x).build());
        });
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteDataset(@RestPath Long id)
    {
        return Panache.withTransaction(() -> 
            datasetRepo.findById(id).onItem()
                .ifNotNull().invoke(x -> x.setDeleted(ZonedDateTime.now()))
            )
            .onItem().ifNotNull().transform(x -> Response.ok().build())
            .onItem().ifNull().continueWith(() -> Response.status(404).build());
    }
}
