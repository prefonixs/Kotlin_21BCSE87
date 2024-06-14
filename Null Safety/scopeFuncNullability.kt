fun main() {
    val list = listOf(5, 3, 1, 4, 2)
    printSortedList(list)
    printSortedList(null)
}

fun printSortedList(list: List<Int>?) {
    list?.run {
        sorted().also {
            println(it)
        }
    } ?: println("List is null")
}