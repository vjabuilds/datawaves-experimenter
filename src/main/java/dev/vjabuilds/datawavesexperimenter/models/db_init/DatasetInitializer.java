package dev.vjabuilds.datawavesexperimenter.models.db_init;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import dev.vjabuilds.datawavesexperimenter.models.Dataset;

@Configuration
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
            repo.saveAll(List.of(
                new Dataset(null,"Dataset 1",
                             "https://kaggle.com", 
                             "A public dataset from kaggle", 
                             "structured"),
                new Dataset(null,"Dataset 2",
                             "https://google.com", 
                             "A public dataset from google", 
                             "image"),
                new Dataset(null,"Dataset 3",
                             "https://amazon.com", 
                             "A public dataset from amazon", 
                             "time_series")
            )).subscribe();
        };
    }    
}
