import kotlinx.coroutines.*

fun main() = runBlocking {
    try {
        withTimeout(2000L) {
            repeat(1000) { i ->
                println("Task running... $i")
                delay(500L)
            }
        }
    } catch (e: TimeoutCancellationException) {
        println("Task timed out")
    }
}