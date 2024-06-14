fun main() {
    printString("hello")
    printString(null)
}
fun printString(str: String?) {
    str?.let {
        println(it)
    } ?: println("String is null")
}