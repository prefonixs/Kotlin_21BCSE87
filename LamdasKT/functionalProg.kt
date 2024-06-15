fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)
    println(processNumbers(numbers))
}

fun processNumbers(numbers: List<Int>): Int {
    return numbers.map { it * it }.filter { it % 2 != 0 }.reduce { acc, i -> acc + i }
}