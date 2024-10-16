package seungkyu.slicetest.repository;

import org.bson.assertions.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;
import seungkyu.slicetest.common.repository.UserEntity;
import seungkyu.slicetest.testBuilder.TestDataBuilder;

@DataR2dbcTest
@Import(TestR2dbcH2Config.class)
@ActiveProfiles("h2")
public class UserR2dbcRepositoryH2SliceTest {

    @Autowired
    UserR2dbcRepository userR2dbcRepository;

    @Autowired
    R2dbcEntityTemplate r2dbcEntityTemplate;

    @Test
    void r2dbcEntityTemplate_should_not_be_null(){
        Assertions.assertNotNull(userR2dbcRepository);
        Assertions.assertNotNull(r2dbcEntityTemplate);
    }

    @BeforeEach
    void setUp(){
        r2dbcEntityTemplate.delete(UserEntity.class)
                .all().block();
    }

    @Test
    void save(){
        //given
        var name = "sk";
        var userEntity = TestDataBuilder.createUnsavedUserEntity(name);

        var result = userR2dbcRepository.save(userEntity);

        StepVerifier.create(result)
                .assertNext(
                        createdUser -> {
                            Assertions.assertNotNull(createdUser.getName());
                        }
                )
                .verifyComplete();
    }
}
