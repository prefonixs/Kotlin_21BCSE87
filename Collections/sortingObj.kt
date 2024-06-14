fun main() {
    val people = listOf(Person("Alice", 30), Person("Bob", 25), Person("Charlie", 25), Person("Dave", 35))
    val sortedPeople = people.sortedWith(compareBy<Person> { it.age }.thenBy { it.name })
    println(sortedPeople)
}

class Person(val name: String, val age: Int){
    override fun toString(): String {
        return "$name : $age"
    }
}