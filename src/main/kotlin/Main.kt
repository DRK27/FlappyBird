package org.example

import javax.swing.JFrame

class Main{
    fun main(){
        val frame = JFrame("Flappy Bird")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(360, 640)
        frame.isResizable = false

        val gamePanel = GamePanel()
        frame.add(gamePanel)

        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }
}
