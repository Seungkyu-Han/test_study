package seungkyu.mockito.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import seungkyu.mockito.test.app.controller.GreetingController;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(
        classes = {
                GreetingController.class
        }
)
public class ContextConfigurationFailedExampleTest {

    @Autowired
    GreetingController greetingController;
    //GreetingService를 의존성으로 가지는데 bean으로 등록이 안되어 있기 때문에 NoSuchBeanDefinitionException이 발생

    @Test
    void when_request_get_then_return_greeting(){
        var who = "world";

        var result = greetingController.greeting(who);

        var expected = "hello world";

        StepVerifier.create(result)
                .expectNext(expected)
                .verifyComplete();
    }
}
