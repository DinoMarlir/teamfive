package de.airblocks.teamfive.lobby.listener

import de.airblocks.teamfive.lobby.inventory.QueueInventory
import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerBlockInteractEvent

object PlayerBlockInteractListener {

    val PLAYER_BLOCK_INTERACT_LISTENER = EventListener.of(PlayerBlockInteractEvent::class.java) { event ->
        val queueInventory = QueueInventory()
        event.player.openInventory(queueInventory)
    }
}