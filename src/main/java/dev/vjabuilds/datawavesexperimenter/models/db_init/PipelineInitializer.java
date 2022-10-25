package dev.vjabuilds.datawavesexperimenter.models.db_init;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import dev.vjabuilds.datawavesexperimenter.models.Pipeline;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class PipelineInitializer {
    @Bean
    @ConditionalOnProperty(
        value = "insert_data",
        havingValue = "true",
        matchIfMissing = false
    )
    public CommandLineRunner populate_pipelines(ReactiveCrudRepository<Pipeline, Long> repo) {

        return (args) -> 
        {
            repo.saveAll(List.of(
                new Pipeline(null, "Pipeline image", "Maps images to edges"),
                new Pipeline(null, "Pipeline NLP", "Maps text to embedded vectors"),
                new Pipeline(null, "Pipeline 1", "Maps images to edges")
            )).subscribe();
        };
    }
}
