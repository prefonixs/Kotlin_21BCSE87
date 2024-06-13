fun main() {
    var square = Square(4.0)
    square.draw()
    square.resize(3.0)

    var triangle = Triangle(3.0, 5.0)
    triangle.draw()
    triangle.resize(1.5)
}

interface Drawable {
    fun draw()
    fun resize(factor: Double)
}

class Square(var side: Double) : Drawable {
    override fun draw() {
        println("Draw a square with side $side")
    }

    override fun resize(factor: Double) {
        side *= factor
        println("Resize the square to side $side")
    }
}

class Triangle(var base: Double, var height: Double) : Drawable {
    override fun draw() {
        println("Draw a triangle with base $base and height $height")
    }

    override fun resize(factor: Double) {
        base *= factor
        height *= factor
        println("Resize the triangle to base $base and height $height")
    }
}
