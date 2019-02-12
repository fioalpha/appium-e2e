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

        //robotCore.scrollView("Button Callbacks")
        val isExpectedTextA = robotCore.matcherText("Home.cell.0.textLabel", "A")
        val isExpectedTextB = robotCore.matcherText("Home.cell.1.textLabel", "B")
        Assert.assertTrue(isExpectedTextA)
        Assert.assertTrue(isExpectedTextB)
//        robotCore.scrollView("Home.cell.5")
        robotCore.clickButton("Home.cell.2")
        val prefix = "DetailViewController"
        val imageView= prefix + ".imageView"
        val titleLabel= prefix + ".titleLabel"
        val priceLabel= prefix + ".priceLabel"

        val isExpectedTextForTitleLabel = robotCore.matcherText(titleLabel, "C")
        val isExpectedTextForPriceLabel = robotCore.matcherText(priceLabel, "R$ 2,00")
        Assert.assertTrue(isExpectedTextForTitleLabel)
        Assert.assertTrue(isExpectedTextForPriceLabel)
        robotCore.clickButton("${prefix}.backButton")

//        robotCore.clickButton("com.afollestad.materialdialogssample:id/basic_buttons")
    }

}

