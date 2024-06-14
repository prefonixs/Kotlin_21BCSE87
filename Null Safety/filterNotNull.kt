fun main() {
    val nullableInts = listOf(1, null, 3, null, 5)
    val nonNullInts = filterNonNullIntegers(nullableInts)
    println(nonNullInts)
}
fun filterNonNullIntegers(list: List<Int?>): List<Int> {
    return list.filterNotNull()
}