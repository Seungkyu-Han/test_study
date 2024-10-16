package seungkyu.reactortest.test

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.Duration

class LastStepExpectTimeoutExampleTest {

    @Test
    fun test1(){
        val mono = Mono.delay(Duration.ofMillis(500))

        StepVerifier.create(mono)
            .expectTimeout(Duration.ofMillis(100))
            .verify()
    }

    @Test
    fun test2(){
        val mono = Mono.delay(Duration.ofMillis(500))

        StepVerifier.create(mono)
            .expectTimeout(Duration.ofMillis(100))
            .verify()
    }

    @Test
    fun test3(){
        val flux = Flux.range(0, 10)
            .delayElements(Duration.ofMillis(500))

        StepVerifier.create(flux)
            .expectTimeout(Duration.ofMillis(100))
            .verify()
    }
}