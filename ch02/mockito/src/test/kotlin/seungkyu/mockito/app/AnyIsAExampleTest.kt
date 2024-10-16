package seungkyu.mockito.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.nullable
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.isA
import org.mockito.kotlin.isNull
import org.mockito.kotlin.mock
import seungkyu.mockito.test.app.service.GreetingService

class AnyIsAExampleTest {

    @Test
    fun test1(){
        val mocked: GreetingService = mock()

        `when`(mocked.greeting(any<String>()))
            .thenReturn("hoi world")

        Assertions.assertEquals("hoi world", mocked.greeting("world"))
    }

    @Test
    fun test2(){
        val mocked: GreetingService = mock()

        `when`(mocked.greeting(nullable(String::class.java)))
            .thenReturn("hoi world")

        Assertions.assertEquals("hoi world", mocked.greeting("ㅇㄹㅇㄹ"))
    }
}