package de.airblocks.teamfive.base.player.permissions

import de.airblocks.teamfive.base.player.permissions.impl.PlayerPermissionLayer
import java.util.*

object PermissionManager {

    // TODO: Layer

    fun hasPermission(uuid: UUID, permission: String): Boolean {
        return PlayerPermissionLayer(uuid).hasPermission(permission)
    }

    fun isDisabled(uuid: UUID, permission: String): Boolean {
        return PlayerPermissionLayer(uuid).isDisabled(permission)
    }

    fun getPermissions(uuid: UUID): Map<String, Boolean> {
        return PlayerPermissionLayer(uuid).getPermissions()
    }
}