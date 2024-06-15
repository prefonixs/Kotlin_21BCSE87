fun main() {
    val strings = listOf("apple", "banana", "mango", "apricot")
    val processed = processStrings(strings,'a')
    println(processed)
}
fun processStrings(strings: List<String>, letter: Char): List<String> {
    return strings.filter { it.startsWith(letter, ignoreCase = true) }.map { it.uppercase() }.sorted()
}
