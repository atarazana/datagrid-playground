package com.olleb;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.infinispan.client.hotrod.RemoteCache;

import com.olleb.model.Example;

import io.quarkus.infinispan.client.Remote;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class CacheManagerService {

    public static final String EXAMPLE_CACHE_NAME = "example";

    @Inject
    @Remote(EXAMPLE_CACHE_NAME)
    RemoteCache<String, Example> examples;

    void onStart(@Observes StartupEvent ev) {
        cleanup();
        loadCacheContent();
    }

    private void loadCacheContent() {
        try {
            // Let's assume that we are retrieving data from various sources and performing
            // operations such as ETL.
            examples.put(
                    "ex1",
                    new Example("n1"));

            examples.put(
                    "ex2",
                    new Example("n2"));


        } catch (Exception e) {
            Log.error("Error loading the cache content!", e);
        }
    }

    private void cleanup() {
        try {
            CompletableFuture.allOf(examples.clearAsync()).get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.error("Error cleaning up the cache!", e);
            Thread.currentThread().interrupt();
        }
    }

}
