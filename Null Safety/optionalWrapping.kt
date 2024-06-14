fun main() {
    println(wrapInOptional("hello"))
    println(wrapInOptional(null))
}
fun <T> wrapInOptional(value: T?): T? {
    return value
}