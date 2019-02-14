import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import core.*
import core.robot.RobotCore
import core.manager.TE
import core.manager.ViewProperties
import org.junit.After
import org.junit.Before
import org.junit.Test

class FlowTest {

    val main = json("main.json")
    val detail = json("details.json")
    val tabbar = json("tabbar.json")
    val more = json("more.json")
    val loginOption = json("loginOption.json")
    val netshoesLogin = json("netshoesLogin.json")

    @Test
    fun test() {
        detailsPage(detail) {
            mainPage(main) {
                scroll()
                click()
            }
            clickBack()
        }
    }

    @Test
    fun testLogin() {
        netshoesLoginPage(netshoesLogin) {
            loginOptionPage(loginOption) {
                morePage(more) {
                    tabbarPage(tabbar) {
                        clickMore()
                    }
                    clickLogin()
                }
                clickNetshoesLogin()
            }
            fillUserField()
            fillPasswordField()
            clickEnter()
        }
    }

}

fun Any.json(nameFile: String): HashMap<*, *> {
    val environment = getEnvironment()
    val text = this.javaClass.getResource("$environment/$nameFile").readText()
    return Gson().fromJson(text, HashMap::class.java)
}

