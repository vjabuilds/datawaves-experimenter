package vjabuilds.dev.repos;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import vjabuilds.dev.models.Artifact;

@ApplicationScoped
public class ArtifactRepo implements PanacheRepository<Artifact> {
    
}
