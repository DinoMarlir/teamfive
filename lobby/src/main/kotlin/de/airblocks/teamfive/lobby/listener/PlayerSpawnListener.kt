package de.airblocks.teamfive.lobby.listener

import de.airblocks.teamfive.lobby.LobbyServer
import de.airblocks.teamfive.lobby.display.QueueBossbarDisplay
import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerSpawnEvent


object PlayerSpawnListener {

    val PLAYER_SPAWN_LISTENER = EventListener.of(PlayerSpawnEvent::class.java) { event ->
        if (event.instance != LobbyServer.INSTANCE) return@of

        QueueBossbarDisplay.addPlayer(event.player)
    }
}