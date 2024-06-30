package de.airblocks.teamfive.lobby.inventory

import de.airblocks.teamfive.lobby.utils.QUEUE_ITEM
import net.minestom.server.entity.Player

fun updateInventoryForPlayer(player: Player) {
    with(player.inventory) {
        clear()
        setItemStack(4, QUEUE_ITEM)
    }
}