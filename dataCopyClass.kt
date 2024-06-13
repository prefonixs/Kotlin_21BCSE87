fun main() {
    var person1 = Person("Sid", 21, "123 ABC St")
    var person2 = person1.copy(age = 31, address = "456 XYZ St")
    println("person1: $person1")
    println("person2: $person2")
}

data class Person(var name: String, var age: Int, var address: String)