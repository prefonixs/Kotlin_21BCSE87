fun main() {
    val list = listOf("Alice" to 30, "Bob" to 25, "Charlie" to 20)
    println(sortByAge(list))
}

fun sortByAge(pairs: List<Pair<String, Int>>): List<Pair<String, Int>> {
    return pairs.sortedBy { it.second }
}
