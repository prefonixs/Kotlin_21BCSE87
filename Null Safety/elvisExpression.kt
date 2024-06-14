fun main() {
    println(sumNullableInts(1, 2))
    println(sumNullableInts(1, null))
    println(sumNullableInts(null, 2))
}
fun sumNullableInts(a: Int?, b: Int?): Int {
    return a?.let { aValue ->
        b?.let { bValue ->
            aValue + bValue
        }
    } ?: -1
}