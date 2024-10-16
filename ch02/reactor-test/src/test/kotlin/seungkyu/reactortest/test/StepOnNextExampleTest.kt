package seungkyu.reactortest.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class StepOnNextExampleTest {

    @Test
    fun test1(){
        val flux = Flux.range(0, 10)

        StepVerifier.create(flux)
            .assertNext{
                Assertions.assertEquals(0, it)
            }
            .expectNext(1, 2)
            .expectNextCount(3)
            .expectNextSequence(listOf(6, 7, 8))
            .expectNextMatches { it == 9 }
            .expectComplete()
            .verify()

    }
}