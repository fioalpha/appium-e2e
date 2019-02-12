package core.android

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.util.concurrent.TimeUnit

class AndroidDriverConfig {

    companion object {
        private const val SERVER = "http://127.0.0.1:4723/wd/hub"
        private const val DEVICE_NAME = "Android Device"
        private const val APP_PATH = "app-debug.apk"
        private const val TYPE_AUTOMATION = "UiAutomator2"
    }

    val driver: AndroidDriver by lazy {
        val cap = DesiredCapabilities().apply {
            setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME)
            setCapability(MobileCapabilityType.APP, APP_PATH)
            setCapability(MobileCapabilityType.AUTOMATION_NAME, TYPE_AUTOMATION)
        }
        AndroidDriver(URL(SERVER), cap)
    }

    fun getResetApp() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
        driver.resetApp()
    }

}