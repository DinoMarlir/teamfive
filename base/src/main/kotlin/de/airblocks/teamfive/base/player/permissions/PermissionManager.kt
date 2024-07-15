package de.airblocks.teamfive.base.player.permissions

import de.airblocks.teamfive.base.player.permissions.group.PermissionGroup
import de.airblocks.teamfive.base.player.permissions.group.PermissionGroupRepository
import de.airblocks.teamfive.base.player.permissions.impl.PlayerPermissionLayer
import java.util.*
import kotlin.reflect.full.primaryConstructor

object PermissionManager {

    // TODO: Layer

    val layers = arrayListOf(
        PlayerPermissionLayer::class
    )

    fun hasPermission(uuid: UUID, permission: String): Boolean {
        return getAllLayers(uuid).any { it.hasPermission(permission) }
    }

    fun isDisabled(uuid: UUID, permission: String): Boolean {
        return getAllLayers(uuid).any { it.isDisabled(permission) }
    }

    fun getPermissions(uuid: UUID): Map<String, Boolean> {
        return PlayerPermissionLayer(uuid).getPermissions()
    }

    private fun getAllLayers(uuid: UUID): List<PermissionLayer> {
        return layers.map {
            it.primaryConstructor!!.call(uuid)
        }
    }
}