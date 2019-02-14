import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import core.MainRobot
import core.detailsPage
import core.getEnvironment
import core.robot.RobotCore
import core.mainPage
import core.manager.TE
import core.manager.ViewProperties
import org.junit.After
import org.junit.Before
import org.junit.Test

class FlowTest {

    val main = json("main.json")
    val detail = json("details.json")

    @Test fun test() {
        detailsPage(detail) {
            mainPage(main) {
                scroll()
                click()
            }
         fillTextField()
         clickBack()
        }
    }

}

fun Any.json(nameFile: String): HashMap<*, *> {
    val environment = getEnvironment()
    val text  = this.javaClass.getResource("$environment/$nameFile").readText()
    return Gson().fromJson(text, HashMap::class.java)
}
