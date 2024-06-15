fun main() {
    val strings = listOf("apple", "banana", "mango")
    val transformed = transformStrings(strings) { it.uppercase() }
    println(transformed)
}
fun transformStrings(strings: List<String>, opperation: (String) -> String): List<String> {
    return strings.map(opperation)
}