package seungkyu.mockito.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import seungkyu.mockito.test.app.service.GreetingService

class StubbingThrowExampleTest {

    @Test
    fun test1(){
        val mocked: GreetingService = mock()

        `when`(mocked.greeting("world"))
            .thenThrow(IllegalStateException::class.java)

        Assertions.assertThrows(IllegalStateException::class.java) {
            mocked.greeting("world")
        }
    }

    @Test
    fun test2(){
        val mocked: GreetingService = mock()

        `when`(mocked.greeting("world"))
        .thenThrow(
            IllegalStateException::class.java,
            ArithmeticException::class.java,
            NullPointerException::class.java
        )

        Assertions.assertThrows(IllegalStateException::class.java) {
            mocked.greeting("world")
        }

        Assertions.assertThrows(ArithmeticException::class.java) {
            mocked.greeting("world")
        }

        Assertions.assertThrows(NullPointerException::class.java) {
            mocked.greeting("world")
        }
    }
}