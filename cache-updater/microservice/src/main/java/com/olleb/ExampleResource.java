package com.olleb;

import org.infinispan.client.hotrod.RemoteCache;

import com.olleb.model.Example;

import io.quarkus.infinispan.client.Remote;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/example")
public class ExampleResource {

    @Inject
    @Remote("example")
    RemoteCache<String, Example> examples;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String example() {
        return examples.get("ex2").toString();
    }
}
