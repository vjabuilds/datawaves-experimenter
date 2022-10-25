package dev.vjabuilds.datawavesexperimenter.repos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import dev.vjabuilds.datawavesexperimenter.models.Pipeline;

public interface PipelineRepository extends ReactiveCrudRepository<Pipeline, Long> {
    
}
