package seungkyu.reactortest.test

import org.junit.jupiter.api.Test
import reactor.test.StepVerifier
import reactor.test.publisher.TestPublisher

class TestPublisherSignalExampleTest {

    @Test
    fun test1(){
        val testPublisher: TestPublisher<Int> = TestPublisher.create()

        StepVerifier.create(testPublisher)
            .then{
                testPublisher.next(1, 2, 3)
            }
            .expectNext(1, 2, 3)
            .then{
                testPublisher.complete()
            }
            .verifyComplete()
    }

    @Test
    fun test2(){
        val testPublisher: TestPublisher<Int> = TestPublisher.create()

        val error = IllegalStateException("test")

        StepVerifier.create(testPublisher)
            .then{
                testPublisher.error(error)
            }
            .verifyErrorMatches { it === error }
    }

    @Test
    fun test3(){
        val testPublisher: TestPublisher<Int> = TestPublisher.create()

        StepVerifier.create(testPublisher)
            .then{testPublisher.next(1, 2, 3)}
            .expectNext(1, 2, 3)
            .verifyComplete()
    }
}