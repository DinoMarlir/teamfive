package de.airblocks.teamfive.base.server

import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.instance.GameServerInstanceFactory
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import java.util.*

abstract class GameServer(
    val id: String,
    val displayName: String
): Thread("GAME_SERVER_THREAD#$displayName") {

    val players: MutableMap<UUID, Player> = mutableMapOf()

    abstract fun enable()
    abstract fun disable()
    abstract fun initializePlayer(player: GamePlayer)
    abstract fun uninitializePlayer(player: GamePlayer)

    open val fallbackStrategy: FallbackStrategy = FallbackStrategy.NoFallback

    fun getInstanceFactory(): GameServerInstanceFactory {
        return GameServerInstanceFactory()
    }

    init {
        isDaemon = true
    }

    override fun run() {
        enable()
    }

    fun stop(
        callback: () -> Unit = {}
    ) {
        disable()
        interrupt()
        callback.invoke()
    }

    fun broadcastMessage(message: Component) {
        players.values.forEach { it.sendMessage(message) }
    }
}