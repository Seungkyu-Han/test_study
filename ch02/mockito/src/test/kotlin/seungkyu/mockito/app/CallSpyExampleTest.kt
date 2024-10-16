package seungkyu.mockito.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.kotlin.never
import org.mockito.kotlin.spy
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import seungkyu.mockito.test.app.service.GreetingService


class CallSpyExampleTest {
    @Test
    fun test1() {
        val spy: GreetingService = spy()
        verify(spy, never()).greeting(anyString())

        var greeting = spy.greeting("world")
        Assertions.assertEquals("hello world", greeting)
        verify(spy).greeting("world")

        `when`(spy.greeting("world"))
            .thenReturn("hoi world")
            .thenCallRealMethod()
            .thenThrow(ArithmeticException::class.java)

        greeting = spy.greeting("world")
        Assertions.assertEquals("hoi world", greeting)
        verify(spy, times(2)).greeting("world")

        greeting = spy.greeting("world")
        Assertions.assertEquals("hello world", greeting)
        verify(spy, times(3)).greeting("world")

        Assertions.assertThrows(ArithmeticException::class.java) {
            spy.greeting("world")
        }
    }
}