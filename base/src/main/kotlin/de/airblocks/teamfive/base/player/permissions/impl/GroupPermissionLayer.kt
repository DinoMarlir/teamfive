package de.airblocks.teamfive.base.player.permissions.impl

import de.airblocks.teamfive.base.player.permissions.PermissionLayer
import java.util.*

class GroupPermissionLayer(val uuid: UUID): PermissionLayer(uuid) {

    /*
    * TODO:
    * - SubLayers:
    * - Default Groups
    * - Player Groups
     */

    override fun hasPermission(permission: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun isDisabled(permission: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPermissions(): Map<String, Boolean> {
        TODO("Not yet implemented")
    }
}