package dev.vjabuilds.datawavesexperimenter.repos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import dev.vjabuilds.datawavesexperimenter.models.MLModel;

public interface MLModelRepository extends ReactiveCrudRepository<MLModel, Long> {
    
}
