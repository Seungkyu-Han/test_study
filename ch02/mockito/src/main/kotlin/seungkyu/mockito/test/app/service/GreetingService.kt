package seungkyu.mockito.test.app.service

import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
@RequiredArgsConstructor
class GreetingService {

    companion object{
        private val log = LoggerFactory.getLogger(GreetingService::class.java)
    }

    fun hello(who: String) {
        val greeting = prepareGreeting(who)
        log.info(greeting)
    }

    fun greeting(who: String?): String {
        return prepareGreeting(who)
    }

    fun greetingMono(who: String): Mono<String>? {
        return Mono.just(prepareGreeting(who))
    }

    fun greetingCount(): Int? {
        return 100
    }

    private fun prepareGreeting(who: String?): String {
        return "hello $who"
    }

    private var count = 100

    private fun setCount(c: Int) {
        this.count = c
    }
}