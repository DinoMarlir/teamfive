package de.airblocks.teamfive.base.server

abstract class GameServer(
    val name: String
): Thread("GAME_SERVER_THREAD#$name") {

    abstract fun enable()
    abstract fun disable()

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