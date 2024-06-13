fun main() {
    val instance=DatabaseConnection
    instance.connect()
    instance.disconnect()
}

object DatabaseConnection {
    init {
        println("DatabaseConnection initialized")
    }

    fun connect() {
        println("Connecting to the database")
    }

    fun disconnect() {
        println("Disconnecting from the database")
    }
}
