fun main() {
    val frame = JFrame()
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.size = Dimension(500, 300)
    frame.layout = GridLayout(0, 2)
    frame.title = "dummy window"
    val sayHelloButton = JButton("say hello")
    sayHelloButton.addActionListener {
        JOptionPane.showMessageDialog(null, "hello!");
    }
    frame.add(sayHelloButton)

    val panel = JPanel()
    panel.layout = GridLayout(0, 1)
    val l = JLabel("?")
    val showSizeButton = JButton("show size")
    showSizeButton.addActionListener {
        l.text = "${frame.size.width} x ${frame.size.height}"
    }
    panel.add(showSizeButton)
    panel.add(l)
    frame.add(panel)

    val panel2 = JPanel()
    panel2.layout = GridLayout(0, 2)
    val increaseButton = JButton("increase")
    increaseButton.addActionListener {
        frame.size = Dimension(frame.size.width*2, frame.size.height*2)
    }
    panel2.add(increaseButton)
    val decreaseButton = JButton("decrease")
    decreaseButton.addActionListener {
        frame.size = Dimension(frame.size.width/2, frame.size.height/2)
    }
    panel2.add(decreaseButton)
    panel.add(panel2)
    frame.isVisible = true
}
