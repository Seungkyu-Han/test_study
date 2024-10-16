package seungkyu.mockito.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import seungkyu.mockito.test.app.controller.GreetingController;
import seungkyu.mockito.test.app.service.GreetingService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(
        classes = {
                GreetingController.class
        }
)
public class ContextConfigurationMockBeanExampleTest {
    @Autowired
    GreetingController greetingController;

    @MockBean
    GreetingService mockGreetingService;

    @Test
    void when_request_get_then_return_greeting(){
        var who = "world";
        var message = "msg";

        when(mockGreetingService.greetingMono(anyString()))
                .thenReturn(Mono.just(message));

        var result = greetingController.greeting(who);

        StepVerifier.create(result)
                .expectNext(message)
                .verifyComplete();
    }
}
