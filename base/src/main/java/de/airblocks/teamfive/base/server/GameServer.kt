package de.airblocks.teamfive.base.server

import de.airblocks.teamfive.base.server.instance.GameServerInstanceFactory
import net.minestom.server.instance.Instance

abstract class GameServer(
    val id: String,
    val name: String
): Thread("GAME_SERVER_THREAD#$name") {

    abstract fun enable()
    abstract fun disable()
    abstract val mainInstance: Instance

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