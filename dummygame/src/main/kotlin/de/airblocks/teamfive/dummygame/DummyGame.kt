package de.airblocks.teamfive.dummygame

import de.airblocks.teamfive.base.games.AbstractGameMode
import net.kyori.adventure.text.Component

class DummyGame: AbstractGameMode() {
    override val name = Component.text("DummyGame")
    override val simpleName = "DummyGame"
    override val description = Component.text("A demo game mode.")
    override val gameServer =
        { id: String, displayName: String ->
            DummyGameServer(id, displayName)
        }
    override val minPlayersToStart = 1
    override val maxPlayers = 12
}