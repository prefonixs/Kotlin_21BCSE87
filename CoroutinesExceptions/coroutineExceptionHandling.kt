import kotlinx.coroutines.*

fun main() = runBlocking {
    val error=launch {
        var x=10
        var y=0
        try {
            val res=x/y
            println(res)
        } catch (e: ArithmeticException ) {
            println("Caught an exception: ${e.message}")
        }
    }
}