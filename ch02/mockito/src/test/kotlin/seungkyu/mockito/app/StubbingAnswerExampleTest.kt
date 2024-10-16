package seungkyu.mockito.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import seungkyu.mockito.test.Greeting
import seungkyu.mockito.test.app.service.GreetingService

class StubbingAnswerExampleTest {

    @Test
    fun test1(){
        val mocked: GreetingService = mock()

        `when`(mocked.greeting(anyString()))
            .thenAnswer{
                val name = it.arguments[0]
                if(name is String && name.equals("seungkyu")){
                    throw ArithmeticException()
                }
                "hoi $name"
            }

        Assertions.assertEquals("hoi world", mocked.greeting("world"))
        Assertions.assertThrows(ArithmeticException::class.java) { mocked.greeting("seungkyu") }
    }
}