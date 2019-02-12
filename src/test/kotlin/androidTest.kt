import core.MainRobot
import core.android.AndroidDriverConfig
import core.ios.IosDriverConfig
import core.robot.AndroidRobotCore
import core.robot.IOSRobotCore
import core.robot.RobotCore
import core.scroll
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class androidTest {
    private lateinit var robotCore: RobotCore

    @Before fun setup() {
        robotCore = MainRobot()
    }

    @After fun tearDown() {
    }

    @Test fun test() {
        scroll {
            scroll("Churros4444")
            matcher()
        }
    }
}

