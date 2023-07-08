fun main() {
    val child1 = Permission("test")
    val child2 = Permission("test 1")
    val child3 = Permission("test 2")
    val parent = Permission("parent", setOf(), setOf(child1, child2), setOf())

    val jim = User("Jim", setOf(parent))
    println(jim.hasPermission(child1))
    println(jim.hasPermission(child3))
    println(parent.getPermissions().map { it.name })
}