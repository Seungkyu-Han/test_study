package seungkyu.reactortest.test

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import reactor.test.StepVerifierOptions
import reactor.util.context.Context

class StepContextExampleTest {

    @Test
    fun test1(){
        val flux: Flux<Int> = Flux.range(0, 5)

        StepVerifier.create(flux)
            .expectNoAccessibleContext()
            .expectNextCount(5)
            .verifyComplete()
    }

    @Test
    fun test2(){
        val flux: Flux<Int> = Flux.range(0, 5)
            .contextWrite(Context.of("foo", "bar"))

        StepVerifier.create(flux)
            .expectAccessibleContext()
            .contains("foo", "bar").then()
            .expectNextCount(5)
            .verifyComplete()
    }

    @Test
    fun test3(){
        val flux = Flux.range(0, 5)

        val options = StepVerifierOptions.create()
            .withInitialContext(Context.of("foo", "bar"))

        StepVerifier.create(flux, options)
            .expectAccessibleContext()
            .contains("foo", "bar").then()
            .expectNextCount(5)
            .verifyComplete()
    }
}