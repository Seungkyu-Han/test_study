package seungkyu.mockito.r2dbc;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import seungkyu.mockito.MockitoApplication;

@ActiveProfiles("mysql-init")
@Import(TestR2dbcMysqlConfig.class)
@ContextConfiguration(classes = MockitoApplication.class)
public class R2InitWithYamlExampleTest {
}
