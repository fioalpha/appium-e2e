package core.android

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.net.URL
import java.util.concurrent.TimeUnit

object  AndroidDriverConfig {

    private const val SERVER = "http://127.0.0.1:4723/wd/hub"
    private const val DEVICE_NAME = "Android Device"
    private const val APP_PATH = "src/test/kotlin/core/android/app-debug.apk"
    private const val TYPE_AUTOMATION = "appium"

    val driver: AndroidDriver by lazy {
        val cap = DesiredCapabilities().apply {
            setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME)
            setCapability(MobileCapabilityType.APP, File(APP_PATH))
            setCapability(MobileCapabilityType.AUTOMATION_NAME, TYPE_AUTOMATION)
            setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60)
        }
        AndroidDriver(URL(SERVER), cap)
    }
}