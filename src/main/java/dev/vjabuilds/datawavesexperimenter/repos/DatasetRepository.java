package dev.vjabuilds.datawavesexperimenter.repos;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import dev.vjabuilds.datawavesexperimenter.models.Dataset;
import reactor.core.publisher.Flux;

public interface DatasetRepository extends ReactiveCrudRepository<Dataset, Long> {
    public Flux<Dataset> findByParentDatasetId(Long pid);
}
