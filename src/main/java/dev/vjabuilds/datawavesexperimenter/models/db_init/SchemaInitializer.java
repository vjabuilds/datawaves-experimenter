package dev.vjabuilds.datawavesexperimenter.models.db_init;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.DatabasePopulator;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
@Order(1)
public class SchemaInitializer {
    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer cfi = new ConnectionFactoryInitializer();
        cfi.setConnectionFactory(connectionFactory);
        try
        {
            cfi.setDatabasePopulator(
                new CompositeDatabasePopulator(
                    Stream.of(
                        new PathMatchingResourcePatternResolver().getResources("schema/*.sql")
                    ).map(
                        r -> (DatabasePopulator)new ResourceDatabasePopulator(r) 
                    ).toList()
                )
            );
        }
        catch(IOException e)
        {

        }
        return cfi;
    }
}
