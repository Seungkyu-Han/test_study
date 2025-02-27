package seungkyu.mockito.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import seungkyu.mockito.test.app.controller.GreetingController;
import seungkyu.mockito.test.app.service.GreetingService;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(
        classes = {
                GreetingController.class,
                GreetingService.class
        }
)
public class ContextConfigurationExampleTest {

    @Autowired
    GreetingController greetingController;


    @Test
    void when_request_get_then_return_greetig() {
        // given
        var who = "world";

        // when
        var result = greetingController.greeting(who);

        // then
        var expected = "hello world";
        StepVerifier.create(result)
                .expectNext(expected)
                .verifyComplete();

    }
}
