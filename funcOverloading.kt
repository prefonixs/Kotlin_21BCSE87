fun main() {
    var recArea=calculateArea(5.0,6.0)
    var cirArea=calculateArea(5.0)
    println("Rectangle area: $recArea")
    println("Circle area: $cirArea")
}

fun calculateArea(length: Double, breadth: Double): Double {
    return length * breadth
}

fun calculateArea(radius: Double): Double {
    return Math.PI * radius * radius
}