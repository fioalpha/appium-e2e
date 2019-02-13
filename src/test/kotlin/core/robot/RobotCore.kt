package core.robot

import core.android.AndroidDriverConfig
import core.ios.IosDriverConfig
import io.appium.java_client.MobileBy
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.junit.Assert
import org.openqa.selenium.WebElement
import java.util.concurrent.TimeUnit
import java.util.HashMap
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebElement


interface RobotCore {

    fun fillEditText(id: String, text: String): RobotCore

    fun clickButton(id: String): RobotCore

    fun matcherText(id: String, textToMatcher: String): RobotCore

    fun scrollView(view: String = "", text: String, action: (WebElement) -> Any = {}): RobotCore

    fun reset(): RobotCore

    fun isVisible(id: String): RobotCore
}


class AndroidRobotCore(
    private val driver: AndroidDriver = AndroidDriverConfig.driver
) : RobotCore {

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

    override fun isVisible(id: String) = apply {
        Assert.assertTrue(getView(id).isDisplayed)
    }
}

class IOSRobotCore(
    private val driver: IOSDriver = IosDriverConfig.driver
): RobotCore {
    override fun clickButton(id: String) = apply {
        getView(id).click()
        driver.findElementByAccessibilityId("Home.cell").click()
        driver.findElementByAccessibilityId(id).click()
    }

    override fun scrollView(view: String, text: String, action: (WebElement) -> Any) = apply {
        val parent = driver.findElement(
            By.className("XCUIElementTypeTable")

        ) as RemoteWebElement //identifying the parent Table
        val parentID = parent.id
        val scrollObject = HashMap<String, String>()
        scrollObject["element"] = parentID

        // Use the predicate that provides the value of the label attribute
        scrollObject["predicateString"] = "name == '$text'"
        driver.executeScript("mobile:scroll", scrollObject)  // scroll to the target element
    }

    override fun reset() = apply {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
        driver.resetApp()
    }

    private fun getView(id: String): WebElement = driver.findElementById(id)

    override fun fillEditText(id: String, text: String) = apply {
        getView(id).sendKeys(text)
    }


    override fun matcherText(id: String, textToMatcher: String) = apply {
        Assert.assertTrue(getView(id).text == textToMatcher)
    }

    override fun isVisible(id: String) = apply {
        Assert.assertTrue(getView(id).isDisplayed)
    }

}

object GetDriver {

    const val ANDROID_DRIVER = 1
    const val IOS_DRIVER = 2

    fun getDriver(type: Int): RobotCore {
        return if (type == ANDROID_DRIVER) {
            AndroidRobotCore()
        } else {
            IOSRobotCore()
        }
    }
}
