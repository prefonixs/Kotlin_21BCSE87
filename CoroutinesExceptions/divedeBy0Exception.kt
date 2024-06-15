fun main() {
    println(divide(10, 5))
    println(divide(10, 0))
}

fun divide(a: Int, b: Int): String {
    try {
        val result = a / b
        return "Result: $result"
    } catch (e: ArithmeticException) {
        return "Error: Division by zero is not allowed"
    }
}