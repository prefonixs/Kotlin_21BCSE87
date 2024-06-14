fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val(even,odd)=partition(numbers)
    println(even)
    println(odd)
}
fun partition(numbers : List<Int>):Pair<List<Int>,List<Int>>{
    val even = numbers.filter { it % 2 == 0 }
    val odd = numbers.filter { it % 2 == 1 }
    return Pair(even,odd)
}