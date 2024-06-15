fun main() {
    try {
        checkAge(17)
    } catch (e: InvalidAgeException) {
        println(e.message)
    }
}
fun checkAge(age: Int) {
    if (age < 18) {
        throw InvalidAgeException("Age must be 18 or older")
    } else {
        println("Age is valid")
    }
}
class InvalidAgeException(message: String) : Exception(message)
