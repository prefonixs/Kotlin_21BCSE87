fun main() {
    var factorial=factorialRecursive(5)
    println(factorial)
}

tailrec fun factorialRecursive(n: Int,accumulator: Int = 1): Int {
    require(n >= 0) { "n must be positive" }
    if (n <= 1) {
        return accumulator
    } else {
        return factorialRecursive(n - 1 , n * accumulator)
    }
}