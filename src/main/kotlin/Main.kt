fun main(args: Array<String>) {
    val child1 = Permission("test", setOf(), setOf(), setOf())
    val child2 = Permission("test 1", setOf(), setOf(), setOf())
    val parent = Permission("parent", setOf(), setOf(child1, child2), setOf())
    println(parent.getPermissions().map { it.name })
}