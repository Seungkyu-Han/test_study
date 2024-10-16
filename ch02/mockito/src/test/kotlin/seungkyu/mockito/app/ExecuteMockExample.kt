package seungkyu.mockito.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import seungkyu.mockito.test.app.service.GreetingService

class ExecuteMockExample {

    @Test
    fun mockMethods(){
        val mocked: GreetingService = mock()

        mocked.hello("world")

        val actualCount = mocked.greetingCount()
        Assertions.assertEquals(0, actualCount)

        val actualGreeting = mocked.greeting("world")
        Assertions.assertNull(actualGreeting)

        val actualMono = mocked.greetingMono("world")
        Assertions.assertNull(actualMono)
    }
}