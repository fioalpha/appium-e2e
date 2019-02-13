package core.robot

import core.android.AndroidDriverConfig
import core.ios.IosDriverConfig
import io.appium.java_client.MobileBy
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.junit.Assert
import org.openqa.selenium.WebElement
import java.util.concurrent.TimeUnit

interface RobotCore {

    fun fillEditText(id: String, text: String): RobotCore

    fun clickButton(id: String): RobotCore

    fun matcherText(id: String, textToMatcher: String): RobotCore

    fun scrollView(view: String, text: String, action: (WebElement) -> Any): RobotCore

    fun reset(): RobotCore
}

class AndroidRobotCore(
    private val driver: AndroidDriver = AndroidDriverConfig.driver
): RobotCore {

    private fun getView(id: String): WebElement = driver.findElementById(id)

    override fun fillEditText(id: String, text: String) = apply {
        getView(id).sendKeys(text)
    }

    override fun clickButton(id: String) = apply {
        getView(id).click()
    }

    override fun matcherText(id: String, textToMatcher: String) = apply {
        print(id)
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS)
        Assert.assertTrue(getView(id).text == textToMatcher)
    }

    override fun scrollView(view: String, text: String, action: (WebElement) -> Any) = apply {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS)
        val element = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"$text\"));")
        action(element)
    }

    override fun reset() = apply {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
        driver.resetApp()
    }

}

class IOSRobotCore(
    private val driver: IOSDriver = IosDriverConfig.driver
): RobotCore {

    override fun reset() = apply {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
        driver.resetApp()
    }

    private fun getView(id: String): WebElement = driver.findElementById(id)

    override fun fillEditText(id: String, text: String) = apply {
        getView(id).sendKeys(text)
    }

    override fun clickButton(id: String) = apply {
        getView(id).click()
    }

    override fun matcherText(id: String, textToMatcher: String) = apply {
        Assert.assertTrue(getView(id).text == textToMatcher)
    }

    override fun scrollView(view: String, text: String, action: (WebElement) -> Any) = apply {
    }

}
