package de.airblocks.teamfive.base.server

import net.minestom.server.entity.Player

abstract class GameServer(
    val name: String
) {

    val players: MutableList<Player> = mutableListOf()

    fun start() {
        enable()
    }

    fun stop(
        callback: () -> Unit = {}
    ) {
        disable()
        callback.invoke()
    }

    abstract fun enable()
    abstract fun disable()
}