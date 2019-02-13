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

        robotCore.matcherText("Home.cell.0.textLabel", "A")
        robotCore.matcherText("Home.cell.1.textLabel", "B")

        val idCell = "Home.cell.11"
        robotCore.isVisible(idCell)
        robotCore.scrollView("", idCell)
        robotCore.clickButton(idCell)


        val prefix = "DetailViewController"
        
        val titleLabel= prefix + ".titleLabel"
        val priceLabel= prefix + ".priceLabel"

        robotCore.matcherText(titleLabel, "L")
        robotCore.matcherText(priceLabel, "R$ 11,00")

        robotCore.clickButton("Home")

        //robotCore.clickButton("${prefix}.confirmButton")
        //robotCore.clickButton("${prefix}.backButton")

//        robotCore.clickButton("com.afollestad.materialdialogssample:id/basic_buttons")
    }

}

