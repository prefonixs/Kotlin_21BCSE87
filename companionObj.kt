fun main() {
    var user1 = User.createUser()
	var user2 = User.createUser(name = "Sid", age = 21)
    println("user1 : ${user1.name} , ${user1.age}")
    println("user2 : ${user2.name} , ${user2.age}")
}

class User(var name: String, var age: Int) {
    companion object {
        fun createUser(name: String = "John", age: Int = 0): User {
            return User(name, age)
        }
    }
}
