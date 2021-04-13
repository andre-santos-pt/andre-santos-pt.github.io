import java.awt.GridLayout
import java.awt.LayoutManager

class DefaultSetup : FrameSetup {
    override val title: String
        get() = "Test"
    override val layoutManager: LayoutManager
        get() = GridLayout(2, 1)
}

class Move : Action {
    override val name: String
        get() = "center"

    override fun execute(window: Window) {
        window.move( 500, 500)
    }
}

class Size : Action {
    override val name: String
        get() = "change size"

    override fun execute(window: Window) {
        window.setSize(500, 500)
    }
}