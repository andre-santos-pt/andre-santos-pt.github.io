
import java.awt.Color
import java.awt.Graphics
import java.awt.GridLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*

typealias IntPair = Pair<Int, Int>

/**
 * Interface for observable objects
 */
interface IObservable<O> {

    // Implementers have to provide this property
    val observers: MutableList<O>

    fun addObserver(observer: O) {
        observers.add(observer)
    }

    fun removeObserver(observer: O) {
        observers.remove(observer)
    }

    fun notifyObservers(handler: (O) -> Unit) {
        observers.toList().forEach { handler(it) }
    }
}

/**
 * Set of integer pairs (no duplicates), serving as the model
 */
class PairDataSet(vararg pairs: IntPair)
    : Iterable<IntPair>, IObservable<(PairDataSet.EventType, IntPair, IntPair?) -> Unit> {

    // TODO 3: observable operations for removing and replacing pairs

    // for classifying modification events
    enum class EventType {
        ADD,
        REMOVE, // TODO
        REPLACE  // TODO
    }

    private val data = mutableSetOf<IntPair>()

    init {
        pairs.forEach {
            data.add(it)
        }
    }

    override fun iterator(): Iterator<Pair<Int, Int>> = data.iterator()

    override val observers: MutableList<(EventType, IntPair, IntPair?) -> Unit> = mutableListOf()

    fun add(p: IntPair) {
        if (data.add(p))
            // fires event to registered observers
            notifyObservers {
                it(EventType.ADD, p, null)
            }
    }

    override fun toString(): String {
        return data.joinToString(separator = "    ") { it.toString() }
    }
}





/**
 * Controller: wires model and view
 */
fun main() {
    val model = PairDataSet(Pair(10, 10), Pair(20, 20), Pair(30, 30))

    val view = window {
        title = "MVC Exercise"
        size = 600 x 300

        content {
            columns = 1
            +panel {
                +PairDataSetLabel(model)
                +button("add") {
                    val pair = dualPrompt("Values?", "first","second", "0", "0")
                    pair?.let {
                        model.add(Pair(pair.first.toInt(), pair.second.toInt()))
                    }
                }
            }
            +DotCanvas(model) // TODO 2: react to clicks
            +TableComponent(model) // TODO 3: react to edits // TODO 4: react to deletes
        }
    }
    view.open()
}


// TODO 1: make this class react to changes in the model
class PairDataSetLabel(private val model: PairDataSet) : JLabel() {
    init {
        text = "$model"
    }
}


// TODO 2: make these objects observable with respect to mouse clicks - observers should obtain the clicked coordinate
class DotCanvas(private val model: PairDataSet) : JComponent() {

    public override fun paintComponent(g: Graphics) {
        g.color = Color.BLACK
        for (d in model) {
            g.fillOval(d.first, d.second, 10, 10)
        }
    }

    init {
        border = BorderFactory.createLineBorder(Color.BLACK)

        // reacts to changes in the model
        model.addObserver { _, _, _ ->
            repaint()
        }

        addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                // TODO: fire click event
                println("mouse click: ${e.x}, ${e.y}")
            }
        })
    }
}


/**
 * View for listing the int pairs in an editable table
 */
class TableComponent(model: PairDataSet) : JPanel(), IObservable<TableComponent.TableEvent> {

    interface TableEvent {
        fun pairModified(old: IntPair, new: IntPair) {}

        // TODO: observable event: delete button click
        //fun pairDeleted(pair: IntPair) {}
    }

    override val observers: MutableList<TableEvent> = mutableListOf()

    init {
        layout = GridLayout(0, 1)
        model.forEach {
            addPair(it)
        }

        // TODO 4: register observer in the model
    }

    private fun addPair(pair: IntPair) {
        add(PairComponent(pair))
        revalidate()
        repaint()
    }

    fun removePair(pair: IntPair) {
        val find = components.find { it is PairComponent && it.matches(pair) }
        find?.let {
            remove(find)
        }
        revalidate()
        repaint()
    }

    fun replacePair(old: IntPair, new: IntPair) {
        val find = components.find { it is PairComponent && it.matches(old) } as? PairComponent
        find?.let {
            find.modify(new)
        }
    }

    inner class PairComponent(var pair: IntPair) : JComponent() {
        val first = JTextField("${pair.first}")
        val second = JTextField("${pair.second}")

        inner class MouseClick(val first: Boolean) : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                val newPair = dualPrompt("new values", "first", "second", pair.first.toString(), pair.second.toString())
                newPair?.let {
                    notifyObservers {
                        it.pairModified(pair, IntPair(newPair.first.toInt(), newPair.second.toInt()))
                    }
                }
            }
        }

        init {
            layout = GridLayout(0, 3)

            first.isEnabled = false
            first.addMouseListener(MouseClick(true))
            add(first)

            second.isEnabled = false
            second.addMouseListener(MouseClick(false))
            add(second)

            add(button("delete") {
                // TODO 4: fire event
            })
        }

        fun modify(new: Pair<Int, Int>) {
            pair = new
            first.text = "${new.first}"
            second.text = "${new.second}"
        }

        fun matches(p: Pair<Int, Int>) = pair == p
    }
}
