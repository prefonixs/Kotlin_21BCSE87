fun main() {
    var rec=Rectangle(5.0,6.0)
    var cir=Circle(5.0)
    println("Rectangle area: ${rec.area()}")
    println("Rectangle perimeter: ${rec.perimeter()}")
    println("Circle area: ${cir.area()}")
    println("Circle perimeter: ${cir.perimeter()}")
}

abstract class Shape {
    abstract fun area(): Double
}

class Rectangle(var length: Double, var breadth: Double) : Shape() {
    override fun area(): Double = length * breadth

    fun perimeter(): Double = 2 * (length + breadth)
}

class Circle(var radius: Double) : Shape() {
    override fun area(): Double = Math.PI * radius * radius

    fun perimeter(): Double = 2 * Math.PI * radius
}