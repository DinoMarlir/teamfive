package de.airblocks.teamfive.lobby

import de.airblocks.teamfive.base.component.BaseComponent

class LobbyComponent: BaseComponent() {
    override fun enable() {
        println("LobbyComponent enabled!")
    }

    override fun disable() {
        println("LobbyComponent disabled!")
    }
}