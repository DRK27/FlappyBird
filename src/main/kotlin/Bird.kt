package org.example

import java.awt.Image


class Bird(var horizontal: Int, var vertical: Int, var width: Int, var height: Int, var img: Image) {
    fun checkCollision(pipe: Pipe): Boolean {
        return this.horizontal < pipe.horizontal + pipe.width && this.horizontal + this.width > pipe.horizontal && this.vertical < pipe.vertical + pipe.height && this.vertical + this.height > pipe.vertical
    }

    fun resetPosition(startY: Int) {
        this.vertical = startY
    }
}