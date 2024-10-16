package seungkyu.mockito.r2dbc;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import seungkyu.mockito.MockitoApplication;
import seungkyu.mockito.test.app.repository.user.UserEntity;
import seungkyu.mockito.test.app.repository.user.UserR2dbcRepository;

@ActiveProfiles("mysql")
@Import({
        TestR2dbcMysqlConfig.class,
        TestR2dbcMysqlInitConfig.class
})
@ContextConfiguration(classes = MockitoApplication.class)
@DataR2dbcTest
public class R2dbcInitializerExampleTest {

    @Autowired
    UserR2dbcRepository userR2dbcRepository;

    @Autowired
    R2dbcEntityTemplate r2dbcEntityTemplate;

    @AfterEach
    void tearDown() {
        r2dbcEntityTemplate.delete(UserEntity.class)
                .all()
                .block();
    }
}
