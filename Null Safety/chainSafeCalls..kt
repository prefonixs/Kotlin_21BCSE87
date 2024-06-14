fun main() {
    val address = Address("Bhubaneswar")
    val personWithAdd = Person(address)
    val personWithoutAdd = Person(null)
    println(getCityName(personWithAddress))
    println(getCityName(personWithoutAddress))
}

class Address(val city: String?)
class Person(val address: Address?)

fun getCityName(person: Person): String {
    return person.address?.city ?: "Unknown City"
}