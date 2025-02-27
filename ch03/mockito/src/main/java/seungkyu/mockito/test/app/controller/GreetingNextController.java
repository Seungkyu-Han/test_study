package seungkyu.mockito.test.app.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import seungkyu.mockito.test.app.service.GreetingNextService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/greeting")
public class GreetingNextController {
    private final GreetingNextService greetingService;

    @GetMapping("/diff")
    public Mono<String> diffGreetingByWho(
            @RequestParam String who
    ) {
        return greetingService.greetingByWho(who);
    }
}