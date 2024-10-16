package seungkyu.reactortest.test

import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class LastStepVerifyExampleTest {

    @Test
    fun test1(){
        val error = IllegalStateException("error")

        StepVerifier.create(Mono.error<Int>(error))
            .verifyErrorMessage("error")

    }
}