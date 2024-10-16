package seungkyu.mockito.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import seungkyu.mockito.MockitoApplication;
import seungkyu.mockito.test.app.controller.GreetingController;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(
        classes = {
                MockitoApplication.class
        }
)
public class ContextConfigurationWithApplicationExampleTest {

    @Autowired
    GreetingController greetingController;

    @Test
    void when_request_get_then_return_greeting() {
        var who = "world";

        var result = greetingController.greeting(who);

        var expected = "hello world";

        StepVerifier.create(result)
                .expectNext(expected)
                .verifyComplete();
    }
}
