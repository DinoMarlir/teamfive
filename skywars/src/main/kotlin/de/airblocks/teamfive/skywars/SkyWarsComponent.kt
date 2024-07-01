package de.airblocks.teamfive.skywars

import de.airblocks.teamfive.base.component.BaseComponent
import de.airblocks.teamfive.base.games.GamesRegistry

class SkyWarsComponent: BaseComponent() {

    override fun enable() {
        GamesRegistry.register(SkyWarsGame(8, 1, 4))
    }
}