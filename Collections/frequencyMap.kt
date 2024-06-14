fun main() {
    val numbers = listOf(1, 2, 2, 3, 3, 3)
    val frequencies = frequencyMap(numbers)
    println(frequencies)
}

fun frequencyMap(numbers: List<Int>): Map<Int, Int> {
    val frequencies = mutableMapOf<Int, Int>()
    for(i in numbers){
        frequencies[i]=frequencies.getOrDefault(i,0)+1
    }
    return frequencies
}
