package core

import core.robot.AndroidRobotCore
import core.robot.IOSRobotCore
import core.robot.RobotCore
import json
import java.io.FileInputStream
import java.util.*
import kotlin.collections.HashMap

class MainRobot:RobotCore by RobotCoreFactory().getInstance() {
    lateinit var items: HashMap<*, *>

    fun scroll() {
        scrollView("", getViewItem(
            "scrollTo", items
        ))
    }

    fun click() {
        clickButton(getViewItem(
            "scrollTo", items
        ))
    }

}

class DetailsRobot: RobotCore by RobotCoreFactory().getInstance() {

    lateinit var items: HashMap<*, *>

    fun matcherContent(){
        matcherText(getViewItem("title", items), "L")
        matcherText(getViewItem("cost", items), "R\$ 11,00")
    }
}
private fun getViewItem(key: String, idItems: HashMap<*, *>): String =  idItems[key] as String

fun mainPage(item: HashMap<*,*>, func: MainRobot.() -> Unit) = MainRobot().apply {
    items = item
    func()
}

fun detailsPage(item: HashMap<*,*>, func: DetailsRobot.() -> Unit) = DetailsRobot().apply {
    items = item
    func()
}

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



