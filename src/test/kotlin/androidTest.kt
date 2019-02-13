import core.MainRobot
import core.detailsPage
import core.robot.RobotCore
import core.mainPage
import org.junit.After
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
        detailsPage {
            mainPage {
                scroll("Churros4444")

            }
            matcherContent()
        }
    }
}

