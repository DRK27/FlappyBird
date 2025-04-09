package org.example.org.example

import org.example.GameLogic
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

import java.awt.*
import java.awt.event.*
import javax.swing.*

class GamePanel : JPanel(), ActionListener, KeyListener {

    private lateinit var backgroundImg: Image
    private lateinit var gameLogic: GameLogic
    private lateinit var placePipeTimer: Timer
    private lateinit var gameLoop: Timer

    init {
        preferredSize = Dimension(360, 640)
        isFocusable = true
        addKeyListener(this)

        // Загрузка ресурсов
        backgroundImg = loadImage("/background.png")
        val birdImg = loadImage("/flappybird.png")
        val topPipeImg = loadImage("/toppipe.png")
        val bottomPipeImg = loadImage("/bottompipe.png")

        // Логика игры
        gameLogic = GameLogic(
            360, 640, 34, 24,
            birdImg, 64, 512, 640 / 4,
            topPipeImg, bottomPipeImg
        )

        // Таймеры
        placePipeTimer = createTimer(1500) { gameLogic.addPipe() }
        gameLoop = createTimer(1000 / 60, this)

        startGame()
    }

    private fun startGame() {
        placePipeTimer.start()
        gameLoop.start()
    }

    private fun stopGame() {
        placePipeTimer.stop()
        gameLoop.stop()
    }

    private fun createTimer(delay: Int, listener: ActionListener): Timer {
        return Timer(delay, listener)
    }

    private fun loadImage(path: String): Image {
        return ImageIcon(javaClass.getResource(path)).image
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }

    private fun draw(g: Graphics) {
        g.drawImage(backgroundImg, 0, 0, width, height, this)
        g.drawImage(
            gameLogic.bird.img,
            gameLogic.bird.horizontal,
            gameLogic.bird.vertical,
            gameLogic.bird.width,
            gameLogic.bird.height,
            this
        )

        for (pipe in gameLogic.pipes) {
            g.drawImage(pipe.img, pipe.horizontal, pipe.vertical, pipe.width, pipe.height, this)
        }

        g.color = Color.white
        g.font = Font("Arial", Font.PLAIN, 32)
        g.drawString(
            if (gameLogic.gameOver) "Game Over: ${gameLogic.score}" else "${gameLogic.score}",
            10,
            35
        )
    }

    override fun actionPerformed(e: ActionEvent) {
        if (gameLogic.gameOver) {
            stopGame()
        } else {
            gameLogic.moveBird()
            gameLogic.movePipes()
            repaint()
        }
    }

    override fun keyPressed(e: KeyEvent) {
        if (e.keyCode == KeyEvent.VK_SPACE) {
            if (gameLogic.gameOver) {
                gameLogic.resetGame()
                startGame()
            } else {
                gameLogic.speedVertical = -9
            }
        }
    }

    override fun keyReleased(e: KeyEvent) {}
    override fun keyTyped(e: KeyEvent) {}
}
