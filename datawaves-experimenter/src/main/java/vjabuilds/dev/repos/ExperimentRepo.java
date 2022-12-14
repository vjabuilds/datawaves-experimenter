package vjabuilds.dev.repos;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import vjabuilds.dev.models.Experiment;

@ApplicationScoped
public class ExperimentRepo implements PanacheRepository<Experiment> {
    
}
