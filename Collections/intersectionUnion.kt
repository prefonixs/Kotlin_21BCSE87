fun main() {
    val list1 = listOf(1, 2, 3, 4)
    val list2 = listOf(3, 4, 5, 6)
    println(intersection(list1, list2))
    println(union(list1, list2))
}

fun intersection(list1: List<Int>, list2: List<Int>): List<Int> {
    val result=list1.filter{it in list2}.distinct()
    return result.sorted()
}

fun union(list1: List<Int>, list2: List<Int>): List<Int> {
    val result=mutableListOf<Int>()
    for (item in list1) {
        if (item !in result) {
            result.add(item)
        }
    }
    for (item in list2) {
        if (item !in result) {
            result.add(item)
        }
    }
    return result.sorted()
}

