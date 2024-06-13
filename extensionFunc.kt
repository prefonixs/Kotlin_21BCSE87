fun main() {
    var word = "racecar"
	println(word.isPalindrome())
}

fun String.isPalindrome(): Boolean {
    return this == this.reversed()
}