package de.airblocks.teamfive.lobby

import de.airblocks.teamfive.base.component.BaseComponent
import de.airblocks.teamfive.base.server.GameServerFactory

class LobbyComponent: BaseComponent() {

    companion object {
        lateinit var instance: LobbyComponent; private set
    }

    override fun enable() {
        instance = this
        println("LobbyComponent enabled!")
        val lobbyServer = LobbyServer("Lobby")
        GameServerFactory.registerServer(lobbyServer)
        GameServerFactory.startServer(lobbyServer)
    }

    override fun disable() {
        println("LobbyComponent disabled!")
    }
}