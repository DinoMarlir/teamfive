package de.airblocks.teamfive.lobby.utils

import de.airblocks.teamfive.lobby.LobbyServer
import de.airblocks.teamfive.lobby.queue.Queue
import net.minestom.server.entity.Player

fun Player.currentQueue(): Queue<*>? {
    return LobbyServer.queues.values.firstOrNull { it.getPlayers().contains(this) }
}