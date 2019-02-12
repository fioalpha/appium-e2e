package core.robot

import core.android.AndroidDriverConfig
import core.ios.IosDriverConfig
import io.appium.java_client.MobileBy
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.WebElement
import java.util.concurrent.TimeUnit

interface RobotCore {

    fun fillEditText(id: String, text: String)

    fun clickButton(id: String)

    fun matcherText(id: String, textToMatcher: String): Boolean

    fun scrollView(view: String)

    fun reset()
}

class AndroidRobotCore(
    private val driver: AndroidDriver = AndroidDriverConfig().driver
): RobotCore {
    override fun reset() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
        driver.resetApp()
    }

    private fun getView(id: String): WebElement = driver.findElementById(id)

    override fun fillEditText(id: String, text: String) {
        getView(id).sendKeys(text)
    }

    override fun clickButton(id: String) {
        getView(id).click()
        Thread.sleep(500)
    }

    override fun matcherText(id: String, textToMatcher: String): Boolean {
        return getView(id).text == textToMatcher
    }

    override fun scrollView(view: String) {
        driver.findElement(
            MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector()).getChildByText(" + "new UiSelector().className(\"android.widget.Button\"), \"File Chooser + Filter\")"
            )
        )
    }

}

class IOSRobotCore(
    private val driver: IOSDriver = IosDriverConfig().driver
): RobotCore {

    override fun reset() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
        driver.resetApp()
    }

    private fun getView(id: String): WebElement = driver.findElementById(id)

    override fun fillEditText(id: String, text: String) {
        getView(id).sendKeys(text)
    }

    override fun clickButton(id: String) {
        getView(id).click()
        Thread.sleep(500)
    }

    override fun matcherText(id: String, textToMatcher: String): Boolean {
        return getView(id).text == textToMatcher
    }

    override fun scrollView(view: String) {
        driver.findElement(
            MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector()).getChildByText(" + "new UiSelector().className(\"android.widget.Button\"), \"File Chooser + Filter\")"
            )
        )
    }

}

object GetDriver {

    const val ANDROID_DRIVER = 1
    const val IOS_DRIVER = 2

    fun getDriver(type: Int): RobotCore {
        return if(type == ANDROID_DRIVER) {
            AndroidRobotCore()
        } else {
            IOSRobotCore()
        }
    }

}
