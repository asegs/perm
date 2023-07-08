class Permission {
    val name: String
    val parents: Set<Permission>
    val additions: Set<Permission>
    val subtractions: Set<Permission>

    constructor(name: String) {
        this.name = name
        this.parents = setOf()
        this.additions = setOf()
        this.subtractions = setOf()
    }

    constructor(name: String, parents: Set<Permission>, additions: Set<Permission>, subtractions: Set<Permission>) {
        this.name = name
        this.parents = parents
        this.additions = additions
        this.subtractions = subtractions
    }

    fun getAllAdditions (): Set<Permission> {
        return additions.fold(setOf(this)) {set, permission -> set + permission.getAllAdditions()}
    }

    fun getAllSubtractions () : Set<Permission> {
        return subtractions.fold(setOf(this)) {set, permission -> set + permission.getAllSubtractions()}
    }

    fun getPermissions () : Set<Permission> {
        return getAllAdditions() - getAllSubtractions() + setOf(this)
    }


}