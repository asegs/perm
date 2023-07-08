class PermissionsTable() {
    val permissionsLookup: MutableMap<String, Permission>
    val permissionsCache: MutableMap<String, Set<Permission>>

    val userLookup: MutableMap<String, User>
    val userCache: MutableMap<String, Set<Permission>>

    init {
        permissionsLookup = mutableMapOf()
        permissionsCache = mutableMapOf()
        userLookup = mutableMapOf()
        userCache = mutableMapOf()
    }

    fun getPermissionsUnder (permissionName: String) : Set<Permission> {
        val possibleCachedPermissions = permissionsCache.getOrDefault(permissionName, null)
        if (possibleCachedPermissions != null) {
            return possibleCachedPermissions
        }

        val possiblePermission = permissionsLookup.getOrDefault(permissionName, null) ?: return setOf()

        val calculatedPermissions = possiblePermission.getPermissions()
        permissionsCache[permissionName] = calculatedPermissions

        return calculatedPermissions
    }

    fun getPermissionsFor (userName: String) : Set<Permission> {
        val possibleCachedPermissions = userCache.getOrDefault(userName, null)

        if (possibleCachedPermissions != null) {
            return possibleCachedPermissions
        }

        val possibleUser = userLookup.getOrDefault(userName, null) ?: return setOf()

        // Not using caching here...
        val calculatedPermissions = possibleUser.allUserPermissions()
        userCache[userName] = calculatedPermissions

        return calculatedPermissions
    }

    //Not finished
    fun userHasPermission (userName: String, permissionName: String) {
        return getPermissionsFor(userName).contains(permissionName)
    }
}