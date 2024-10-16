package seungkyu.mockito.r2dbc;

import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@EnableR2dbcAuditing
@EnableR2dbcRepositories(
        basePackages = "seungkyu.mockito.test.app.repository"
)
@TestConfiguration
public class TestR2dbcH2Config {

    @Bean
    ConnectionFactoryInitializer connectionFactoryInitializer(
            ConnectionFactory connectionFactory
    ) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(
                new ResourceDatabasePopulator(
                        new ClassPathResource("sql/h2.sql")
                )
        );

        return initializer;

    }

    @Slf4j
    @TestConfiguration
    public static class TestR2dbcMysqlInitConfig {

        @Bean
        public ConnectionFactoryInitializer connectionFactoryInitializer(
                ConnectionFactory connectionFactory
        ) {
            ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
            initializer.setConnectionFactory(connectionFactory);
            initializer.setDatabasePopulator(
                    new ResourceDatabasePopulator(
                            new ClassPathResource("sql/mysql.sql")
                    )
            );

            return initializer;
        }
    }
}
