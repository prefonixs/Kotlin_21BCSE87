fun main() {
    val mixedList=listOf(1, "apple", 2.5, "banana", true)
    val strings = filterStrings(mixedList)
    println(strings)
}
fun filterStrings(list: List<Any>): List<String> {
    return list.mapNotNull { it as? String }
}