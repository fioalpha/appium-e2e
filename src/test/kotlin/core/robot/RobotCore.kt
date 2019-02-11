package core.robot

import io.appium.java_client.MobileBy
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.WebElement

interface RobotCore {

    fun fillEditText(id: String, text: String)

    fun clickButton(id: String)

    fun matcherText(id: String, textToMatcher: String): Boolean

    fun scrollView(view: String)
}

class AndroidRobotCore(
    private val driver: AndroidDriver
): RobotCore {

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