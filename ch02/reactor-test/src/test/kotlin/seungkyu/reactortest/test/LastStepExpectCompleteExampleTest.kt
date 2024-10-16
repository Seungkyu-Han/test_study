package seungkyu.reactortest.test

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import reactor.test.StepVerifier.Step

class LastStepExpectCompleteExampleTest {
    @Test
    fun test1(){

        val flux = Flux.range(0, 5)

        StepVerifier.create(flux)
            .expectNextCount(5)
            .expectComplete()
            .verify()
    }

    @Test
    fun test2(){
        val flux: Flux<Int> = Flux.error(IllegalStateException())

        StepVerifier.create(flux)
            .expectComplete()
            .verify()
    }
}