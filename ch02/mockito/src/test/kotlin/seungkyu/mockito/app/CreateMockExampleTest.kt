package seungkyu.mockito.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import seungkyu.mockito.test.app.service.GreetingService

class CreateMockExampleTest {

    @Test
    fun createMock(){
        val mocked: GreetingService = mock()

        Assertions.assertInstanceOf(GreetingService::class.java, mocked)
    }

}