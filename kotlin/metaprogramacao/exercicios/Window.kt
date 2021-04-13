import java.awt.*
import java.util.*
import javax.swing.JButton
import javax.swing.JFrame

interface FrameSetup {
    val title: String
    val layoutManager: LayoutManager
}

interface Action {
    val name: String
    fun execute(window: Window)
}

class Window {
    private val frame = JFrame()

    // 1) eliminar dependencia de DefaultSetup; @Inject
    private val setup: FrameSetup = DefaultSetup()

    // 2) eliminar dependencias das acoes concretas (Center, Size); @InjectAdd
    private val actions = mutableListOf<Action>(Move(), Size())

    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(300, 200)
    }

    val location get() = frame.location
    val dimension get() = frame.size

    fun open() {
        frame.title = setup.title
        frame.layout = setup.layoutManager
        actions.forEach { action ->
            val button = JButton(action.name)
            button.addActionListener { action.execute(this@Window) }
            frame.add(button)
        }
        frame.isVisible = true
    }

    fun move(x: Int, y: Int) {
        frame.location = Point(x, y)
    }

    fun setSize(width: Int, height: Int) {
        require(width > 0)
        require(height > 0)
        frame.size = Dimension(width, height)
    }


}

fun main () {
    val w = Window() // substituir por criacao com injecao
    w.open()
}
