package seungkyu.mockito.app

import org.junit.jupiter.api.Test
import org.mockito.Mockito.atLeast
import org.mockito.Mockito.mock
import org.mockito.kotlin.atMost
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import seungkyu.mockito.test.app.service.GreetingService

class VerificationTimesTest {

    @Test
    fun test1(){
        val mocked: GreetingService = mock()


        for(i in 0..2) mocked.hello("hello")

        verify(mocked, never()).hello("seungkyu")
        verify(mocked, times(3)).hello("hello")
        verify(mocked, atLeast(3)).hello("hello")
        verify(mocked, atLeast(0)).hello("hello")
        verify(mocked, atMost(3)).hello("hello")
        verify(mocked, atMost(99)).hello("hello")
    }
}