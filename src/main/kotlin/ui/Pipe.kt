package ui

import java.awt.Image

class Pipe(var horizontal: Int, var vertical: Int, var width: Int, var height: Int, var img: Image) {
    var passed: Boolean = false
}