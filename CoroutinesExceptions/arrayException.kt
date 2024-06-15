fun main() {
    val arr = arrayOf(1, 2, 3)
    println(accessArrayElement(arr, 2))
    println(accessArrayElement(arr, 5))
}

fun accessArrayElement(arr: Array<Int>, index: Int): Int? {
    try {
        return arr[index]
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("Error: Index out of bounds")
        return null
    } finally {
        println("End of operation")
    }
}