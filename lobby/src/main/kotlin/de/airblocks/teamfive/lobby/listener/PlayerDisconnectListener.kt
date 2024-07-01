package de.airblocks.teamfive.lobby.listener

import de.airblocks.teamfive.lobby.utils.currentQueue
import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerDisconnectEvent

object PlayerDisconnectListener {

    val PLAYER_DISCONNECT_LISTENER = EventListener.of(PlayerDisconnectEvent::class.java) { event ->
        val player = event.player
        player.currentQueue()?.removePlayer(player)
    }
}