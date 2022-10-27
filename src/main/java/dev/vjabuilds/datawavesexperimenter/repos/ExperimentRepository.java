package dev.vjabuilds.datawavesexperimenter.repos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import dev.vjabuilds.datawavesexperimenter.models.Experiment;

public interface ExperimentRepository extends ReactiveCrudRepository<Experiment, Long> {
    
}
