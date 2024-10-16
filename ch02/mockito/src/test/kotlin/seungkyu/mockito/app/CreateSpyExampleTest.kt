package seungkyu.mockito.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.spy
import seungkyu.mockito.test.app.service.GreetingService

class CreateSpyExampleTest {

    class Foo(
        val name: String
    ){
        fun bar(){}
    }

    @Test
    fun createSpy(){
        val spy: GreetingService = spy()

        Assertions.assertNotNull(spy)
    }

    @Test
    fun createSpyByObj(){
        val obj = GreetingService()
        val spy = spy(obj)

        Assertions.assertNotNull(spy)
    }
}
