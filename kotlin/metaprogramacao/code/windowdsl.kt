fun main() {
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
