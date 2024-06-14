fun main() {
    val strings = listOf("apple", "banana", "avocado", "blueberry", "cherry")
    val grouped = group(strings)
    val counts = count(grouped)
    println(grouped)
    println(counts)
}
fun group(strings : List<String>):Map<Char,List<String>>{
    val result=mutableMapOf<Char,MutableList<String>>()
    for(i in strings){
        if (result.containsKey(i[0])) {
            result[i[0]]?.add(i)
        } else {
            result[i[0]] = mutableListOf(i)
        }
    }
    return result
}
fun count(grouped : Map<Char,List<String>>):Map<Char,Int>{
    val result=mutableMapOf<Char,Int>()
    for ((key, value) in grouped) {
        result[key] = value.size
    }
    return result
}

