fun main() {
    val nullableInts = listOf(1, null, 3, null, 5)
    val doubled = doubleNonNullValues(nullableInts)
    println(doubled)
}
fun doubleNonNullValues(list: List<Int?>): List<Int> {
    return list.mapNotNull { it?.let { it * 2 } }
}