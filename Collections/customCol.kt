fun main() {
    val uniqueList = UniqueList<Int>()
    uniqueList.add(1)
    uniqueList.add(1)
    uniqueList.add(2)
    uniqueList.add(3)
    uniqueList.remove(1)
    uniqueList.printList()
}

class UniqueList<T> {
    private val items = mutableListOf<T>()

    fun add(element: T): Boolean {
        if (!items.contains(element)) {
            items.add(element)
            return true
        } else {
            return false
        }
    }

    fun remove(element: T): Boolean {
        return items.remove(element)
    }

    fun contains(element: T): Boolean {
        return items.contains(element)
    }

    fun printList(){
        println(items)
    }
}