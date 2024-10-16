package seungkyu.reactortest.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux

class FluxBlockingTest {

    @Test
    fun test1(){
        val flux:Flux<Int> = Flux.create{
            for(i in 1..10){
                it.next(i)
            }
            it.complete()
        }

        val expected = (1..10).toList()
        val actual = flux.collectList().block()

        Assertions.assertIterableEquals(expected, actual)
    }

    @Test
    fun test2(){
        val flux:Flux<Int> = Flux.create{
            for(i in 1..10){
                it.next(i)
                if(i == 5)
                    it.error(RuntimeException(""))
            }
            it.complete()
        }

        Assertions.assertThrows(RuntimeException::class.java){flux.collectList().block()}
    }
}