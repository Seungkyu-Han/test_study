package seungkyu.reactortest.test

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class StepUtilityExampleTest {

    @Test
    fun test1(){
        val flux: Flux<Int> = Flux.range(0, 10)

        StepVerifier.create(flux, 5)
            .expectSubscription()
            .expectNextCount(5)
            .`as`("five")
            .then{println("five elements")}
            .thenRequest(5)
            .expectNextCount(5)
            .verifyComplete()

    }
}