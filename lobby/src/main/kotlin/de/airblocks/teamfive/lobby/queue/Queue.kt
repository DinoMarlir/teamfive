package de.airblocks.teamfive.lobby.queue

import de.airblocks.teamfive.base.games.AbstractGameMode
import de.airblocks.teamfive.lobby.queue.exception.PlayerAlreadyInQueueException
import de.airblocks.teamfive.lobby.queue.exception.QueueFullException
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player

// TODO: Implement game start in queue
abstract class Queue<G: AbstractGameMode> {
    private val playersIn: MutableList<Player> = mutableListOf()

    abstract val name: Component

    abstract val minPlayersToStart: Int
    abstract val maxPlayers: Int

    fun addPlayer(player: Player) {
        if (playersIn.contains(player)) {
            throw PlayerAlreadyInQueueException()
        }
        if (playersIn.size < maxPlayers) {
            playersIn.add(player)
            player.sendMessage(Component.text("You got added into the queue ").append(name))
            return
        }

        throw QueueFullException()
    }

    fun removePlayer(player: Player) {
        playersIn.remove(player)
        player.sendMessage(Component.text("You got removed from the queue ").append(name))
    }

    fun getPlayers() = playersIn
}