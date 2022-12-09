import testing.Testing
import view.GUI

fun main(args: Array<String>) {
    val test = Testing()
    test.testIntegerType()
    test.testTimeType()
    val gui = GUI()
    gui.showMenu()
}
