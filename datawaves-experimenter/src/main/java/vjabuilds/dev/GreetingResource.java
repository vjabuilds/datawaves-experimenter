package vjabuilds.dev;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import vjabuilds.dev.models.Dataset;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Dataset hello() {
        return new Dataset(1l, "jovan", "https://vjabuilds.dev", "The jovan dataset", "structured", null, null);
    }
}