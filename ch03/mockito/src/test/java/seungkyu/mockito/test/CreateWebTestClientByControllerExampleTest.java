package seungkyu.mockito.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import seungkyu.mockito.test.app.controller.GreetingController;
import seungkyu.mockito.test.app.controller.GreetingControllerAdvice;
import seungkyu.mockito.test.app.service.GreetingService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        GreetingController.class,
        GreetingControllerAdvice.class
})
public class CreateWebTestClientByControllerExampleTest {

    @MockBean
    GreetingService mockGreetingService;

    @Autowired
    GreetingController greetingController;

    @Autowired
    GreetingControllerAdvice greetingControllerAdvice;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(
                greetingController
        ).corsMappings(
                cors -> cors.addMapping("/api/**")
        ).controllerAdvice(greetingControllerAdvice).build();
    }
}
