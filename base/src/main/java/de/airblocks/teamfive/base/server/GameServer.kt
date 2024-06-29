package de.airblocks.teamfive.base.server

import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.instance.GameServerInstanceFactory

abstract class GameServer(
    val id: String,
    val displayName: String
): Thread("GAME_SERVER_THREAD#$displayName") {

    abstract fun enable()
    abstract fun disable()
    abstract fun initializePlayer(player: GamePlayer)

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
}