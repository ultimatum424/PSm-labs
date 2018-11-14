
object IDGenerator {
    private var id: Long = 0

    fun generateId(): Long{
        id++
        println(id)
        return id
    }
}