package core

import core.robot.AndroidRobotCore
import core.robot.IOSRobotCore
import core.robot.RobotCore
import json
import java.io.FileInputStream
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap
import kotlin.concurrent.thread

class MainRobot:RobotCore by RobotCoreFactory().getInstance() {
    lateinit var items: HashMap<*, *>

    fun scroll() {
        scrollView("", getViewItem(
            "scrollTo", items
        ))
    }

    fun click() {
        clickButton(getViewItem(
            "select", items
        ))
    }


}

class DetailsRobot: RobotCore by RobotCoreFactory().getInstance() {

    lateinit var items: HashMap<*, *>

    fun matcherContent(){
        matcherText(getViewItem("title", items), "L")
        matcherText(getViewItem("cost", items), "R\$ 11,00")
    }

    fun clickBack() {
        clickButton( getViewItem(
            "back", items
        ))
    }
}

class TabbarRobot: RobotCore by RobotCoreFactory().getInstance() {

    lateinit var items: HashMap<*, *>

    fun clickMore() {
        Thread.sleep(500)
        clickButton( getViewItem(
            "more", items
        ))
    }
}

class MoreRobot: RobotCore by RobotCoreFactory().getInstance() {

    lateinit var items: HashMap<*, *>

    fun clickLogin() {
        clickButton( getViewItem(
            "login", items
        ))
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

fun tabbarPage(item: HashMap<*,*>, func: TabbarRobot.() -> Unit) = TabbarRobot().apply {
    items = item
    func()
}

fun morePage(item: HashMap<*,*>, func: MoreRobot.() -> Unit) = MoreRobot().apply {
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



