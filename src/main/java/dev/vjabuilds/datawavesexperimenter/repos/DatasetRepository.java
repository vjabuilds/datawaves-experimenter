package dev.vjabuilds.datawavesexperimenter.repos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import dev.vjabuilds.datawavesexperimenter.models.Dataset;

public interface DatasetRepository extends ReactiveCrudRepository<Dataset, Long> {
    
}
