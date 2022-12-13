package vjabuilds.dev.repos;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import vjabuilds.dev.models.MLModel;

@ApplicationScoped
public class MLModelRepo implements PanacheRepository<MLModel> {
    
}
