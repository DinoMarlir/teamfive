package de.airblocks.teamfive.lobby.queue

import de.airblocks.teamfive.base.games.AbstractGameMode
import de.airblocks.teamfive.lobby.queue.exception.QueueFullException
import net.minestom.server.entity.Player

abstract class Queue<G: AbstractGameMode> {
    private val playersIn: MutableList<Player> = mutableListOf()

    abstract val minPlayersToStart: Int
    abstract val maxPlayers: Int

    fun addPlayer(player: Player) {
        if (playersIn.size < maxPlayers) {
            playersIn.add(player)
            return
        }

        throw QueueFullException()
    }

    fun removePlayer(player: Player) {
        playersIn.remove(player)
    }
}