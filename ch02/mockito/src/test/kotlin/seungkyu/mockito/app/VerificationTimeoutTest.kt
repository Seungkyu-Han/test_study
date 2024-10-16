package seungkyu.mockito.app

import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.timeout
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import seungkyu.mockito.test.app.service.GreetingService
import java.util.concurrent.CompletableFuture

class VerificationTimeoutTest {

    @Test
    fun test1(){
        val mocked: GreetingService = mock()

        mocked.hello("world")

        CompletableFuture.runAsync{
            try{
                Thread.sleep(500)
            }catch(e: Exception){
                throw RuntimeException("error")
            }
            mocked.hello("world")
            mocked.hello("world")
            mocked.hello("world")
        }

        verify(mocked, times(1)).hello("world")
        val mode = timeout(1000).times(4)
        verify(mocked, mode).hello("world")
    }
}