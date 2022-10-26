package dev.vjabuilds.datawavesexperimenter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dev.vjabuilds.datawavesexperimenter.models.Dataset;
import dev.vjabuilds.datawavesexperimenter.repos.DatasetRepository;
import dev.vjabuilds.datawavesexperimenter.services.DatasetFetchingService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class DatasetController {

    DatasetRepository repo;
    DatasetFetchingService dfs;

    @GetMapping("/datasets")
    Flux<Dataset> getDatasets()
    {
        return repo.findAll();
    }

    @GetMapping("/datasets/{dataset_id}/with_parents")
    Flux<Dataset> getDatasetWithParents(@PathVariable Long dataset_id)
    {
        return dfs.getDatasetWithParents(dataset_id);
    }

}