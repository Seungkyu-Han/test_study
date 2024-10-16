package seungkyu.mockito.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.*
import seungkyu.mockito.test.Greeting
import seungkyu.mockito.test.app.service.GreetingService

class ArgumentMatcherEqExampleTest {

    @Test
    fun test1(){
        val mocked: GreetingService = mock()

        `when`(mocked.greeting(eq("world")))
            .thenReturn("hoi world")

        Assertions.assertEquals("hoi world", mocked.greeting("world"))

        verify(mocked).greeting("world")
    }
}