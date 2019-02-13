package core

import core.robot.AndroidRobotCore
import core.robot.IOSRobotCore
import core.robot.RobotCore
import java.io.FileInputStream
import java.util.*

class MainRobot:RobotCore by RobotCoreFactory().getInstance() {

    fun scroll(test: String) = scrollView("", test) {
        it.click()
    }

}

fun mainPage(func: MainRobot.() -> Unit) = MainRobot().apply { func() }

class DetailsRobot: RobotCore by RobotCoreFactory().getInstance() {
    fun matcherContent() = matcherText("br.com.netchurros:id/detail_name_textView", "Churros4444")
}

fun detailsPage(func: DetailsRobot.() -> Unit) = DetailsRobot().apply { func() }

class RobotCoreFactory{
    fun getInstance(): RobotCore{
        val environment = getEnvironment()
        return if(environment == "android") AndroidRobotCore()
        else IOSRobotCore()
    }
}

fun getEnvironment(): String{
    val prop = Properties()
    return try {
        val input = FileInputStream("gradle.properties")
        prop.load(input)
        prop.getProperty("environment")
    } catch (ex: Exception) {
        ex.printStackTrace()
        "android"
    }
}

