package de.airblocks.teamfive.base.player.permissions

import java.util.*

abstract class PermissionLayer(uuid: UUID) {

    abstract fun hasPermission(permission: String): Boolean
    abstract fun isDisabled(permission: String): Boolean
    abstract fun getPermissions(): Map<String, Boolean>
}