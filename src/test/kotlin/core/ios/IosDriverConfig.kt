package core.ios

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.util.concurrent.TimeUnit

object IosDriverConfig {

    private const val SERVER = "http://127.0.0.1:4723/wd/hub"
    private const val DEVICE_NAME = "iPhone 8 Plus"
    private const val APP_PATH = "https://github.com/appium/ios-uicatalog.git"
    private const val TYPE_AUTOMATION = "UiAutomator2"

    val driver: IOSDriver by lazy {
        val cap = DesiredCapabilities().apply {
            setCapability(MobileCapabilityType.PLATFORM, "iOS")
            setCapability("platformVersion", "12.1")
            setCapability("deviceName", DEVICE_NAME)
//            setCapability(MobileCapabilityType.APP, APP_PATH)
//            setCapability(MobileCapabilityType.AUTOMATION_NAME, TYPE_AUTOMATION)
            setCapability("bundleId", "br.com.netshoesapp")
//            setCapability("app", "Users/wesley.silva/Library/Developer/Xcode/DerivedData/AutomationAppTest-bmbufymclmkineefbgjithqbanrp/Build/Products/Debug-iphonesimulator/AutomationAppTest.app" )
        }

        IOSDriver(URL(SERVER), cap)
    }


}