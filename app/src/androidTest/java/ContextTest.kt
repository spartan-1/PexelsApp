import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContextTest {
    /**
     * Test method to test app context
     */
    @Test
    fun testAppContext() {
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        Assert.assertEquals("com.pnr.pexelsapp", appContext.packageName)
    }
}