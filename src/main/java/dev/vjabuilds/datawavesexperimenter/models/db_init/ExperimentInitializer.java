package dev.vjabuilds.datawavesexperimenter.models.db_init;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import dev.vjabuilds.datawavesexperimenter.models.Dataset;
import dev.vjabuilds.datawavesexperimenter.models.Experiment;
import dev.vjabuilds.datawavesexperimenter.models.MLModel;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
@AllArgsConstructor
public class ExperimentInitializer {

    private ReactiveCrudRepository<Experiment, Long> experimentRepo;
    private ReactiveCrudRepository<MLModel, Long> mlModelRepo;
    private ReactiveCrudRepository<Dataset, Long> datasetRepo;

    @Bean
    @ConditionalOnProperty(
        value = "insert_data",
        havingValue = "true",
        matchIfMissing = false
    )
    public CommandLineRunner populate_experiments() {
        return (args) -> {
            var model = mlModelRepo.save(new MLModel(null, 
                                    "resnet100", 
                                    "The resnet 100 model architecture", 
                                    "v1", 
                                    "/data/configs/resnet100.yaml", 
                                    List.of(3, 224, 224), 
                                    List.of(100)));
            var ds = datasetRepo.save(new Dataset(null,
                                    "Dataset for experiments",
                                    "https://example.com", 
                                    "A dataset that will be used for traiing a model", 
                                    "image", null, null));
            experimentRepo.saveAll(Mono.zip(ds, model).map(x -> new Experiment(
                null, 
                "Experiment 1", 
                "Applies resnet to classify images",
                x.getT2().getMlModelId(), 
                x.getT1().getDatasetId()
            ))).subscribe();
        };
    }
}
