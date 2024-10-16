package seungkyu.reactortest.test

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class LastStepExpectErrorExample {

    @Test
    fun test1(){
        val flux: Flux<Int> = Flux.error(IllegalStateException())
        StepVerifier.create(flux)
            .expectError()
            .verify()
    }

    @Test
    fun test2(){
        val flux: Flux<Int> = Flux.error(IllegalStateException())
        StepVerifier.create(flux)
            .expectError(IllegalStateException::class.java)
            .verify()
    }

    @Test
    fun test3(){
        val message = "custom message"
        val flux: Flux<Int> = Flux.error(IllegalStateException(message))
        StepVerifier.create(flux)
            .expectErrorMessage(message)
            .verify()
    }

    @Test
    fun test4(){
        val flux: Flux<Int> = Flux.error(IllegalStateException("hello"))

        StepVerifier.create(flux)
            .expectErrorMatches {
                it is IllegalStateException && it.message.equals("hello")
            }.verify()
    }


}