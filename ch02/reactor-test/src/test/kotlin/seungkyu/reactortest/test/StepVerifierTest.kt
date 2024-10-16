package seungkyu.reactortest.test

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class StepVerifierTest {

    @Test
    fun test1(){
        val flux:Flux<Int> = Flux.create{
            for (i in 1..10) {
                it.next(i)
            }
            it.complete()
        }

        StepVerifier.create(flux)
            .expectSubscription()
            .expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .expectComplete()
            .verify()
    }
}