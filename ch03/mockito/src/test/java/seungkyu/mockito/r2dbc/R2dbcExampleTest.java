package seungkyu.mockito.r2dbc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import reactor.test.StepVerifier;
import seungkyu.mockito.test.app.repository.user.UserEntity;
import seungkyu.mockito.test.app.repository.user.UserR2dbcRepository;

@ActiveProfiles("h2")
@Import(TestR2dbcH2Config.class)
@ContextConfiguration(
        classes = R2dbcExampleTest.class
)
@DataR2dbcTest
public class R2dbcExampleTest {

    @Autowired
    UserR2dbcRepository userR2dbcRepository;

    @Autowired
    DatabaseClient databaseClient;

    @Autowired
    R2dbcEntityTemplate r2dbcEntityTemplate;

    @BeforeEach
    void setUp(){
        databaseClient.sql("CREATE TABLE IF NOT EXISTS USERS (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL," +
                        "age INT NOT NULL," +
                        "profile_image_id VARCHAR(255) NOT NULL," +
                        "password VARCHAR(255) NOT NULL," +
                        "created_at       DATETIME     NOT NULL," +
                        "updated_at       DATETIME     NOT NULL" +
                        ");")
                .fetch()
                .rowsUpdated()
                .block();
    }

    @Test
    void when_save_then_it_should_save_it(){
        //given
        UserEntity user = new UserEntity(
                "seungkyu", 26, "1234", "1204"
        );

        var result = userR2dbcRepository.save(user);

        StepVerifier.create(result)
                .assertNext(
                        it -> {
                            Assertions.assertEquals(user.getName(), it.getName());
                            Assertions.assertEquals(user.getAge(), it.getAge());
                            Assertions.assertEquals(user.getProfileImageId(), it.getProfileImageId());
                            Assertions.assertEquals(user.getPassword(), it.getPassword());
                            Assertions.assertNotNull(it.getId());
                        }
                )
                .verifyComplete();
    }

    @Test
    void when_find_by_name_should_return_result(){
        var name = "seungkyu";

        UserEntity user = new UserEntity(
                name, 26, "1234", "1204"
        );
        r2dbcEntityTemplate.insert(UserEntity.class)
                .using(user)
                .block();

        var result = userR2dbcRepository.findByName(name);
        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
    }

    @AfterEach
    void tearDown(){
        databaseClient.sql("DROP TABLE USERS;")
                .fetch()
                .rowsUpdated()
                .block();
    }
}
