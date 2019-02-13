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

    @Test fun testIOS() {

        val isExpectedTextA = robotCore.matcherText("Home.cell.0.textLabel", "A")
        val isExpectedTextB = robotCore.matcherText("Home.cell.1.textLabel", "B")
        Assert.assertTrue(isExpectedTextA)
        Assert.assertTrue(isExpectedTextB)
        val idCell = "Home.cell.11"
        val isVisible = robotCore.isVisible(idCell)
        if (!isVisible) {
            robotCore.scrollView(idCell)
        }
        robotCore.clickButton(idCell)


        val prefix = "DetailViewController"
        
        val titleLabel= prefix + ".titleLabel"
        val priceLabel= prefix + ".priceLabel"

        val isExpectedTextForTitleLabel = robotCore.matcherText(titleLabel, "L")
        val isExpectedTextForPriceLabel = robotCore.matcherText(priceLabel, "R$ 11,00")
        Assert.assertTrue(isExpectedTextForTitleLabel)
        Assert.assertTrue(isExpectedTextForPriceLabel)

        robotCore.clickButton("Home")

        //robotCore.clickButton("${prefix}.confirmButton")
        //robotCore.clickButton("${prefix}.backButton")

//        robotCore.clickButton("com.afollestad.materialdialogssample:id/basic_buttons")
    }

}

