fun main() {
    val user1 = User("Alice", "alice@example.com")
    val user2 = User(null, "bob@example.com")
    printUserDetails(user1)
    printUserDetails(user2)
}

data class User(val name: String?, val email: String?)

fun printUserDetails(user: User) {
    if (user.name == null || user.email == null) {
        println("Incomplete User")
    } else {
        println("Name: ${user.name}, Email: ${user.email}")
    }
}