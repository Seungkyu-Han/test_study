package seungkyu.mockito.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import seungkyu.mockito.test.app.service.GreetingService

class CaptureArgumentExampleTest {

    @Test
    fun captureArgument(){
        val mocked: GreetingService = mock()

        mocked.greeting("world1")
        mocked.greeting("world2")
        mocked.greeting("world3")

        val captor = argumentCaptor<String>()
        verify(mocked, times(3)).greeting(captor.capture())

        val expected = listOf("world1", "world2", "world3")
        Assertions.assertIterableEquals(expected, captor.allValues)
    }
}