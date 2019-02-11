import core.android.AndroidDriverConfig
import core.robot.AndroidRobotCore
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import java.util.concurrent.TimeUnit
import io.appium.java_client.MobileBy



class androidTest {

    private lateinit var androidDriver: AndroidDriver

    private lateinit var robotCore: AndroidRobotCore

    @Before fun setup() {
//        androidDriver = AndroidDriverConfig().driver
        robotCore = AndroidRobotCore(AndroidDriverConfig().driver)
    }

    @After fun tearDown() {
        AndroidDriverConfig().getResetApp()
    }

    @Test fun test() {
//        Assert.assertTrue(
//            robotCore.matcherText("com.afollestad.materialdialogssample:id/basic_buttons", "Basic + Buttons")
//        )

        robotCore.scrollView("Button Callbacks")

//        robotCore.clickButton("com.afollestad.materialdialogssample:id/basic_buttons")
    }

}

