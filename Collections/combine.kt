fun main() {
    val list1 = listOf(1, 2, 3)
    val list2 = listOf(3, 4, 5, 6)
    val list = combine(list1 , list2)
    println(list)
}

fun combine(list1: List<Int>, list2: List<Int>): List<Int> {
    val result=mutableListOf<Int>()
    var i=0
    while(i < list1.size && i < list2.size){
        result.add(list1[i])
        result.add(list2[i])
        i++
    }
    while(i < list1.size){
        result.add(list1[i])
        i++
    }
    while(i < list2.size){
        result.add(list2[i])
        i++
    }
    return result
}

