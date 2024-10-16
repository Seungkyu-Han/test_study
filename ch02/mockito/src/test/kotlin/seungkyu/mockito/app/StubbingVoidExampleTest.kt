package seungkyu.mockito.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import seungkyu.mockito.test.app.service.GreetingService

class StubbingVoidExampleTest {

    @Test
    fun test1(){
        val mocked: GreetingService = mock()

        doThrow(ArithmeticException::class.java)
            .`when`(mocked)
            .hello("seungkyu")

        doNothing()
            .`when`(mocked)
            .hello("world")

        doReturn("hoi world")
            .`when`(mocked)
            .greeting("world")

        Assertions.assertThrows(java.lang.ArithmeticException::class.java) {
            mocked.hello("grizz")
        }
        Assertions.assertDoesNotThrow { mocked.hello("world") }
        Assertions.assertEquals("hoi world", mocked.greeting("world"))
    }
}