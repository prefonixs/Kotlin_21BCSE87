fun main() {
    val strings = listOf("apple", "banana", "cherry", "date")
    val filteredPred=filterStr(strings,predicate="e")
    println(filteredPred)
    val filteredLen=filterStr(strings,length=4)
    println(filteredLen)
}
fun filterStr(strings : List<String>,predicate : String="",length : Int=0):List<String>{
    var filtered=strings.filter{it.contains(predicate)}
    filtered=filtered.filter{it.length>length}
    return filtered
}

