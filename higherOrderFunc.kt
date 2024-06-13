fun main() {
    var sum = operation(10, 5, ::add)
    println("Sum: $sum")

    var difference = operation(10, 5, ::subtract)
    println("Difference: $difference")
    
    var product = operation(10, 5 , { x, y -> x * y })
    println("Product: $product")
}

fun operation(a: Int, b: Int, func: (Int, Int) -> Int): Int {
    return func(a, b)
}

fun add(x: Int, y: Int): Int {
    return x + y
}

fun subtract(x: Int, y: Int): Int {
    return x - y
}