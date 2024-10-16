package seungkyu.mockito.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import seungkyu.mockito.MockitoApplication;
import seungkyu.mockito.test.app.controller.GreetingController;
import seungkyu.mockito.test.app.service.GreetingService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ContextConfiguration(
        classes = MockitoApplication.class
)
@WebFluxTest(controllers = GreetingController.class)
public class WebfluxExampleTest {

    @MockBean
    GreetingService greetingService;

    @Autowired
    WebTestClient webTestClient;

    @Test
    void when_call_then_return_greeting(){
        //given
        String message = "msg";
        when(greetingService.greetingMono(anyString()))
                .thenReturn(Mono.just(message));

        webTestClient.get()
                .uri("/greeting?who=seungkyu")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo(message);
    }

}
