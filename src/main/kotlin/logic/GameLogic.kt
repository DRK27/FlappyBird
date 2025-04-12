package logic

import ui.Bird
import ui.Main
import ui.Pipe
import java.awt.Image
import java.util.ArrayList

class GameLogic(
    private val boardWidth: Int,
    private val boardHeight: Int,
    birdWidth: Int,
    birdHeight: Int,
    birdImg: Image,
    private val pipeWidth: Int,
    private val pipeHeight: Int,
    private val openingSpace: Int,
    private val topPipeImg: Image,
    private val bottomPipeImg: Image
) {

    private var speedHorizontal = -4
    private var gravity = 0.5f
    var speedVertical = 0f
    var gameOver = false
    var score = 0.0
    val pipes: ArrayList<Pipe> = ArrayList()
    val bird = Bird(boardWidth / 8, boardHeight / 2, birdWidth, birdHeight, birdImg)

    fun moveBird() {
        speedVertical += gravity
        bird.vertical += speedVertical.toInt()
    }

    fun movePipes() {
        for (pipe in pipes) {
            pipe.horizontal += speedHorizontal
            if (!pipe.passed && bird.horizontal > pipe.horizontal + pipe.width) {
                score += 0.5
                pipe.passed = true
            }
            if (bird.checkCollision(pipe)) gameOver = true
        }
        if (bird.vertical > boardHeight) gameOver = true
    }

    fun resetGame() {
        bird.resetPosition(boardHeight / 2)
        speedVertical = 0f
        pipes.clear()
        gameOver = false
        score = 0.0
    }


















































    fun addPipe() {
        val randomPipeY = (-boardHeight / 4 - Math.random() * (pipeHeight / 2)).toInt()
        pipes.add(Pipe(boardWidth, randomPipeY, pipeWidth, pipeHeight, topPipeImg))
        pipes.add(Pipe(boardWidth, randomPipeY + pipeHeight + openingSpace, pipeWidth, pipeHeight, bottomPipeImg))
    }
}
fun main() {
    Main().main()
}