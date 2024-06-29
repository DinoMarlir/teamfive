package de.airblocks.teamfive.base.server

import de.airblocks.teamfive.base.server.instance.GameServerInstanceFactory
import net.minestom.server.instance.Instance

abstract class GameServer(
    val id: String,
    val displayName: String
): Thread("GAME_SERVER_THREAD#$displayName") {

    abstract fun enable()
    abstract fun disable()
    abstract val mainInstance: Instance

    open val isFallback: Boolean = false

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