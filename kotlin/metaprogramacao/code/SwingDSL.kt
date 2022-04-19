import java.awt.*
import javax.swing.*

fun main() {
    swing()
    dsl()
}

fun swing() {
    val frame = JFrame()
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.size = Dimension(500, 300)
    frame.layout = GridLayout(0, 2)
    frame.title = "dummy window"
    val b1 = JButton("say hello")
    b1.addActionListener {
        JOptionPane.showMessageDialog(null, "hello!");
    }
    frame.add(b1)

    val panel = JPanel()
    panel.layout = GridLayout(0, 1)
    val l = JLabel("?")
    val b3 = JButton("show size")
    b3.addActionListener {
        l.text = "${frame.size.width} x ${frame.size.height}"
    }
    panel.add(b3)

    panel.add(l)
    frame.add(panel)

    val panel2 = JPanel()
    panel2.layout = GridLayout(0, 2)
    val moveRight = JButton("increase")
    moveRight.addActionListener {
        frame.size = Dimension(frame.size.width*2, frame.size.height*2)
    }
    panel2.add(moveRight)
    val moveDown = JButton("decrease")
    moveDown.addActionListener {
        frame.size = Dimension(frame.size.width/2, frame.size.height/2)
    }
    panel2.add(moveDown)
    panel.add(panel2)
    frame.isVisible = true
}

fun dsl() {
    val window = window {
        title = "dummy window"
        size = 500 x 300
        content {
            columns = 2
            +button("say hello") {
                "Message" dialog "hello!"
            }
            +panel {
                columns = 1
                +button("show size") {
                    val l: JLabel = this@window["sizeLabel"]
                    l.text = size.asText
                }
                +label("?") - "sizeLabel"
                +panel {
                    columns = 2
                    +button("increase") {
                        size = size.width*2 x size.height*2
                    }
                    +button("decrease") {
                        size = size.width/2 x size.height/2
                    }
                }
            }
        }
    }
    window.open()
}



infix fun Int.x(h: Int) = Dimension(this, h)

infix fun String.dialog(msg: String) {
    JOptionPane.showMessageDialog(null, msg, this, JOptionPane.INFORMATION_MESSAGE)
}

val Dimension.asText: String
    get() = "$width x $height"

class Window {
    private val frame = JFrame()
    private val panel = Panel()
    private val childrenIds: MutableMap<String, JComponent> = mutableMapOf()

    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(300, 300)
        frame.add(panel.panel)
    }

    fun open() {
        frame.isVisible = true
    }

    var title: String by frame::title

    var size: Dimension
        get() = frame.size
        set(value) { frame.size = value }

    fun content(build: Panel.() -> Unit) {
        build(panel)
    }

    operator fun <T : JComponent> get(a: String): T {
        check(childrenIds.contains(a))
        return childrenIds[a] as T
    }

    operator fun <T : JComponent> T.minus(id: String): T {
        childrenIds[id] = this
        return this
    }
}


fun window(build: Window.() -> Unit = {}): Window {
    val window = Window()
    build(window)
    return window
}

fun label(text: String): JLabel = JLabel(text)

fun button(text: String, action: () -> Unit): JButton {
    val b = JButton(text)
    b.addActionListener { action() }
    return b
}


fun panel(build: Panel.() -> Unit): JPanel {
    val p = Panel()
    build(p)
    return p.panel
}

class Panel  {
    val panel = JPanel()

    operator fun JComponent.unaryPlus(): JComponent {
        panel.add(this)
        return this
    }

    var columns: Int
        get() = if (panel.layout is GridLayout) (panel.layout as GridLayout).columns else 0
        set(value) {
            panel.layout = GridLayout(0, value)
        }
}


