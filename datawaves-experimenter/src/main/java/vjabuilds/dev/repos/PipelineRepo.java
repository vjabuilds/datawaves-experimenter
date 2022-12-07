package vjabuilds.dev.repos;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import vjabuilds.dev.models.Pipeline;

@ApplicationScoped
public class PipelineRepo implements PanacheRepository<Pipeline> {
    
}
