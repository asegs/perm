import java.security.Permissions

class User {
    val id: String
    val roles: Set<Permission>

    constructor(id: String) {
        this.id = id
        this.roles = setOf()
    }

    constructor(id: String, roles: Set<Permission>) {
        this.id = id
        this.roles = roles
    }

    fun allUserPermissions () : Set<Permission> {
        return roles.fold(setOf()) {set, role -> set + role.getPermissions()}
    }

    fun hasPermission(permissionInQuestion: Permission): Boolean {
        return allUserPermissions().contains(permissionInQuestion)
    }
}

