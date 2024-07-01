package de.airblocks.teamfive.dummygame

import de.airblocks.teamfive.base.component.BaseComponent
import de.airblocks.teamfive.base.games.GamesRegistry

class DummyGameComponent: BaseComponent() {

    override fun enable() {
        GamesRegistry.register(DummyGame())
    }
}