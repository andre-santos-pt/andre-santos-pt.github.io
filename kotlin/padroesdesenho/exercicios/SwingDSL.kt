
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.*


infix fun Int.x(h: Int) = Dimension(this, h)

infix fun String.dialog(msg: String) {
    JOptionPane.showMessageDialog(null, msg, this, JOptionPane.INFORMATION_MESSAGE)
}

val Dimension.asText: String
    get() = "$width x $height"

class Window {
    private val frame = JFrame()
    val panel = Panel()
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
        set(value) {
            frame.size = value
        }

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

enum class Orientation {
    VERTICAL, HORIZONTAL
}

class Panel(val panel: JPanel, build: Panel.() -> Unit = {}) {

    constructor() : this(JPanel())

    init {
        build(this)
    }

    operator fun <T : JComponent> T.unaryPlus(): T {
        panel.add(this)
        return this
    }

    var columns: Int
        get() = if (panel.layout is GridLayout) (panel.layout as GridLayout).columns else 0
        set(value) {
            panel.layout = GridLayout(0, value)
        }

    // TODO
    var box: Orientation?
        get() = if (panel.layout is BoxLayout)
            when ((panel.layout as BoxLayout).axis) {
                BoxLayout.X_AXIS -> Orientation.HORIZONTAL
                BoxLayout.Y_AXIS -> Orientation.VERTICAL
                else -> null
            } else null
        set(value) {
            panel.layout = BoxLayout(
                panel,
                if (value == Orientation.HORIZONTAL) BoxLayout.X_AXIS
                else BoxLayout.Y_AXIS
            )
        }
}

fun dualPrompt(msg: String, first: String, second: String, firstValue: String? = null, secondValue: String? = null): Pair<String, String>? {
    val firstField = JTextField(5)
    firstValue?.let {
        firstField.text = firstValue
    }
    val secondField = JTextField(5)
    secondValue?.let {
        secondField.text = firstValue
    }

    val myPanel = JPanel()
    myPanel.add(JLabel("$first:"))
    myPanel.add(firstField)
    myPanel.add(Box.createHorizontalStrut(15)) // a spacer

    myPanel.add(JLabel("$second:"))
    myPanel.add(secondField)

    val result = JOptionPane.showConfirmDialog(
        null, myPanel,
        msg, JOptionPane.OK_CANCEL_OPTION
    )
    return if (result == JOptionPane.OK_OPTION) Pair(firstField.text, secondField.text) else null
}
