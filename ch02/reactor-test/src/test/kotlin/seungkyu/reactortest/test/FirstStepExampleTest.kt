package seungkyu.reactortest.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import reactor.test.StepVerifierOptions
import reactor.util.context.Context

class FirstStepExampleTest {

    @Test
    fun test1(){
        val flux = Flux.range(1, 10)

        val options = StepVerifierOptions.create()
            .initialRequest(100)
            .withInitialContext(Context.empty())
            .scenarioName("test1")

        StepVerifier.create(flux, options)
            .expectSubscription()
            .expectNextCount(10)
            .verifyComplete()
    }
}