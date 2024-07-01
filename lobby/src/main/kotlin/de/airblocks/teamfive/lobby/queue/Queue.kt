package de.airblocks.teamfive.lobby.queue

import de.airblocks.teamfive.base.games.AbstractGameMode
import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.GameServerFactory
import de.airblocks.teamfive.base.utils.generateId
import de.airblocks.teamfive.lobby.queue.exception.PlayerAlreadyInQueueException
import de.airblocks.teamfive.lobby.queue.exception.QueueFullException
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import net.minestom.server.timer.Task

// TODO: Implement game start in queue
abstract class Queue<G: AbstractGameMode>(val gameMode: G, val startQueue: Boolean = true) {
    private val playersIn: MutableList<Player> = mutableListOf()
    var runnable: Task

    abstract val name: Component
    abstract val simpleName: String

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

    fun startNewGame() {
        val generateId = generateId(8)
        val gameServer = gameMode.gameServer(generateId, "queue-${gameMode.simpleName}-$generateId")

        GameServerFactory.registerServer(gameServer).startWithCallback {
            getPlayers().forEach {
                val gamePlayer = it as GamePlayer
                gamePlayer.sendToServer(gameServer)
            }
            
            playersIn.clear()
        }
    }

    init {
        runnable  = QueueRunnableImpl(this).createQueueRunnable()
        if (startQueue && !runnable.isAlive) runnable
    }
}