package dev.vjabuilds.datawavesexperimenter.models.db_init;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import dev.vjabuilds.datawavesexperimenter.models.MLModel;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class MLModelInitializer {
    @Bean
    @ConditionalOnProperty(
        value = "insert_data",
        havingValue = "true",
        matchIfMissing = false
    )
    public CommandLineRunner populate_models(ReactiveCrudRepository<MLModel, Long> repo) {
        return (args) -> 
        {
            repo.saveAll(List.of(
                new MLModel(null, 
                        "resnet50", 
                        "The resnet 50 model architecture", 
                        "v1", 
                        "/data/configs/resnet50.yaml", 
                        List.of(3, 224, 224), 
                        List.of(100)),
                new MLModel(null, 
                        "resnet50", 
                        "The resnet 50 model architecture augmented with pre-activation", 
                        "v2", 
                        "/data/configs/resnet50_v2.yaml", 
                        List.of(3, 224, 224), 
                        List.of(100))
            )).subscribe();
        };
    }
}
