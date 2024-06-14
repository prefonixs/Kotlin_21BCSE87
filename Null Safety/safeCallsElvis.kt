fun main() {
    println(getLength("hello"))
    println(getLength(null))
}
fun getLength(str: String?): Int {  
    return str?.length ?: -1
}