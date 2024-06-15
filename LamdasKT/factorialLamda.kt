fun main() {
    println(factorial(5))
}
fun factorial(n: Int): Int {
    val fact: (Int) -> Int = { num -> if (num <= 1) 1 else num * factorial(num - 1) }
    return fact(n)
}