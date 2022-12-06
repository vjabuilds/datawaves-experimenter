package vjabuilds.dev.repos;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import vjabuilds.dev.models.Dataset;

@ApplicationScoped
public class DatasetRepo implements PanacheRepository<Dataset> {

}

