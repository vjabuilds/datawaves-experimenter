package dev.vjabuilds.datawavesexperimenter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vjabuilds.datawavesexperimenter.models.Dataset;
import dev.vjabuilds.datawavesexperimenter.repos.DatasetRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class DatasetController {

    DatasetRepository repo;

    @GetMapping("/datasets")
    Flux<Dataset> getDatasets()
    {
        return repo.findAll();
    }
}
