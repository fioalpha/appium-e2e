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
    private val driver: AndroidDriver = AndroidDriverConfig().driver
): RobotCore {

    private fun getView(id: String): WebElement = driver.findElementById(id)

    override fun fillEditText(id: String, text: String): RobotCore {
        getView(id).sendKeys(text)
        return this
    }

    override fun clickButton(id: String):RobotCore {
        getView(id).click()
        Thread.sleep(500)
        return this
    }

    override fun matcherText(id: String, textToMatcher: String): RobotCore {
        Assert.assertTrue(getView(id).text == textToMatcher)
        return this
    }

    override fun scrollView(view: String, text: String, action: (WebElement) -> Any): RobotCore {
        val element = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"$text\"));")
        action(element)
        return this
    }

    override fun reset(): RobotCore {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
        driver.resetApp()
        return this
    }

}

class IOSRobotCore(
    private val driver: IOSDriver = IosDriverConfig().driver
): RobotCore {

    override fun reset() : RobotCore {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
        driver.resetApp()
        return this
    }

    private fun getView(id: String): WebElement = driver.findElementById(id)

    override fun fillEditText(id: String, text: String) : RobotCore {
        getView(id).sendKeys(text)
        return this
    }

    override fun clickButton(id: String): RobotCore {
        getView(id).click()
        Thread.sleep(500)
        return this
    }

    override fun matcherText(id: String, textToMatcher: String): RobotCore {
        Assert.assertTrue(getView(id).text == textToMatcher)
        return this
    }

    override fun scrollView(view: String, text: String, action: (WebElement) -> Any): RobotCore {
        driver.findElement(
            MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector()).getChildByText(" + "new UiSelector().className(\"android.widget.Button\"), \"File Chooser + Filter\")"
            )
        )
        return this
    }

}
