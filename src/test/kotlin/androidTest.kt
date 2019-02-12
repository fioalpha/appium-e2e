import core.android.AndroidDriverConfig
import core.ios.IosDriverConfig
import core.robot.AndroidRobotCore
import core.robot.GetDriver
import core.robot.IOSRobotCore
import core.robot.RobotCore
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import java.util.concurrent.TimeUnit
import io.appium.java_client.MobileBy
import io.appium.java_client.TouchAction
import java.rmi.server.RemoteObject
import java.util.HashMap
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebElement




class androidTest {

    private lateinit var androidDriver: AndroidDriver

    private lateinit var robotCore: RobotCore

    @Before fun setup() {
        robotCore = GetDriver.getDriver(GetDriver.IOS_DRIVER)
    }

    @After fun tearDown() {
        robotCore.reset()
    }

    @Test fun test() {
//        Assert.assertTrue(
//            robotCore.matcherText("com.afollestad.materialdialogssample:id/basic_buttons", "Basic + Buttons")
//        )

//        robotCore.scrollView("Button Callbacks")
//          robotCore.clickButton("Home.cell.3")
//          robotCore.clickButton("confirmButton")
//          robotCore.clickButton("Home")
        robotCore.scrollView("Home.cell.11")

//        robotCore.clickButton("com.afollestad.materialdialogssample:id/basic_buttons")
    }

}

