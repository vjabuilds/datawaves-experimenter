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

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class DatasetInitializer{
    @Bean
    @ConditionalOnProperty(
        value = "insert_data",
        havingValue = "true",
        matchIfMissing = false
    )
    public CommandLineRunner populate(ReactiveCrudRepository<Dataset, Long> repo) {

        return (args) -> 
        {
            repo.saveAll(
            repo.saveAll(List.of(
                    new Dataset(null,"Dataset 1",
                                "https://kaggle.com", 
                                "A public dataset from kaggle", 
                                "structured", null, null),
                    new Dataset(null,"Dataset 2",
                                "https://google.com", 
                                "A public dataset from google", 
                                "image", null, null),
                    new Dataset(null,"Dataset 3",
                                "https://amazon.com", 
                                "A public dataset from amazon", 
                                "time_series", null, null)
                )).takeLast(1).map(arg->new Dataset(null, 
                                    "Derived Dataset", 
                                    "https://kaggle.com", 
                                    "A dataset derived from a public dataset", 
                                    "structured", 
                                    arg.getDataset_id(), 
                                    null))
            ).subscribe();
        };
    }    
}
