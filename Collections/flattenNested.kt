fun main() {
    val nestedLists = listOf(listOf(1, 2, 2), listOf(3, 4, 5), listOf(5, 6))
	val flattenedList = flatten(nestedLists)
	println(flattenedList)
}
fun flatten(nestedLists : List<List<Int>>):List<Int>{
    val result=mutableListOf<Int>()
    for(i in nestedLists){
        for(j in i){
            if(j !in result){
                result.add(j)
            }
        }
    }
    return result
}

