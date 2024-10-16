package seungkyu.mockito.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import seungkyu.mockito.test.app.service.GreetingService

class StubbingReturnExampleTest {

    @Test
    fun test1(){
        val mocked: GreetingService = mock()

        `when`(mocked.greeting("world"))
            .thenReturn("hi world")

        Assertions.assertEquals("hi world", mocked.greeting("world"))
        Assertions.assertEquals("hi world", mocked.greeting("world"))
    }

    @Test
    fun test2(){
        val mocked: GreetingService = mock()

        `when`(mocked.greeting("world"))
            .thenReturn(
                "hello world",
                "hoi world",
                "hi world"
            )

        Assertions.assertEquals("hello world", mocked.greeting("world"))
        Assertions.assertEquals("hoi world", mocked.greeting("world"))
        Assertions.assertEquals("hi world", mocked.greeting("world"))
        Assertions.assertEquals("hi world", mocked.greeting("world"))
    }

    @Test
    fun test3(){
        val mocked: GreetingService = mock()

        `when`(mocked.greeting("world"))
            .thenReturn("hi world")
            .thenReturn("hello world")

        Assertions.assertEquals("hi world", mocked.greeting("world"))
        Assertions.assertEquals("hello world", mocked.greeting("world"))
    }
}