fun main() {
    val add = createAdder(5)
    println(add(10))
}
fun createAdder(x: Int): (Int) -> Int {
    return { y -> x + y }
}