package seungkyu.mockito.test;

import org.junit.jupiter.api.Assertions;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                GreetingController.class
        })
public class WebTestClientStatusExampleTest {

    @MockBean
    GreetingService mockGreetingService;

    @Autowired
    GreetingController greetingController;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp(){
        webTestClient = WebTestClient.bindToController(
                greetingController
        ).build();
    }

    @Test
    void when_call_then_return_greeting1(){
        String message = "msg";

        when(mockGreetingService.greetingMono(anyString()))
                .thenReturn(Mono.just(message));

        webTestClient.get()
                .uri("/greeting?who=seungkyu")
                .exchange()
                .expectStatus()
                .isOk()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void when_call_then_return_greeting2(){
        String message = "msg";

        when(mockGreetingService.greetingMono(anyString()))
                .thenReturn(Mono.just(message));

        //when
        webTestClient.get()
                .uri("/greeting/header?who=seungkyu&age=26")
                .exchange()
                .expectHeader()
                .contentType("text/plain;charset=UTF-8")
                .expectHeader()
                .exists("X-WHO")
                .expectHeader()
                .doesNotExist("X-EMAIL")
                .expectHeader()
                .value(
                        "X-WHO", header -> {
                            Assertions.assertEquals("seungkyu", header);
                        }
                )
                .expectHeader()
                .exists("X-AGE");
    }
}
