package seungkyu.mockito.app

import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import seungkyu.mockito.test.app.service.GreetingService

class VerifyArgsTest {

    @Test
    fun test1(){
        val mocked: GreetingService = mock()

        doReturn("hoi return")
            .`when`(mocked)
            .greeting("world")

        mocked.greeting("world")

        verify(mocked).greeting("world")
        verify(mocked).greeting(eq("world"))
        verify(mocked).greeting(argThat{
            s -> s.equals("world")
        })
    }
}