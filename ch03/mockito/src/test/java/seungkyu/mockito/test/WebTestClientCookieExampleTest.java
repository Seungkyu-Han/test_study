package seungkyu.mockito.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import seungkyu.mockito.test.app.controller.GreetingController;
import seungkyu.mockito.test.app.service.GreetingService;

import java.time.Duration;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static reactor.core.publisher.Mono.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                GreetingController.class,
        }
)
public class WebTestClientCookieExampleTest {
    @MockBean
    GreetingService mockGreetingService;

    @Autowired
    GreetingController greetingController;

    WebTestClient webTestClient;

    @BeforeEach
    void setup() {
        webTestClient = WebTestClient.bindToController(
                greetingController
        ).build();
    }

    @Test
    void when_call_then_return_greeting(){
        String message = "msg";

        lenient().when(mockGreetingService.greetingMono(anyString()))
                .thenReturn(Mono.just(message));

        var cookieName = "who";

        webTestClient.get()
                .uri("/greeting/cookie?who=seungkyu")
                .exchange()
                .expectCookie().exists(cookieName)
                .expectCookie().valueEquals(cookieName, "seungkyu")
                .expectCookie().domain(cookieName, "grizz.kim")
                .expectCookie().httpOnly(cookieName, true)
                .expectCookie().path(cookieName, "/")
                .expectCookie().secure(cookieName, true)
                .expectCookie()
                .maxAge(cookieName, Duration.ofSeconds(3600));
    }

}
