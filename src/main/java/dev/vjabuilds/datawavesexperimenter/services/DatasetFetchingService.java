package dev.vjabuilds.datawavesexperimenter.services;

import org.springframework.stereotype.Component;

import dev.vjabuilds.datawavesexperimenter.models.Dataset;
import dev.vjabuilds.datawavesexperimenter.repos.DatasetRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class DatasetFetchingService {

    private DatasetRepository datasetRepository;

    private Mono<Dataset> getParentDataset(Dataset ds)
    {
        if(ds.getParentDatasetId() != null)
            return datasetRepository.findById(ds.getParentDatasetId());
        else
            return Mono.empty();
    }

    public Flux<Dataset> getDatasetWithParents(long Id)
    {
        return datasetRepository.findById(Id).expand(this::getParentDataset);
    }
    
    public Flux<Dataset> getOriginalDatasets()
    {
        return datasetRepository.findByParentDatasetId(null);
    }
}