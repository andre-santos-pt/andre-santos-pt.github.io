fun main() {
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
