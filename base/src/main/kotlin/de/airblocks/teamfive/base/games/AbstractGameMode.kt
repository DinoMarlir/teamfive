package de.airblocks.teamfive.base.games

import de.airblocks.teamfive.base.server.GameServer
import net.kyori.adventure.text.Component

abstract class AbstractGameMode {
    abstract val name: Component
    abstract val description: Component

    abstract val gameServer: (id: String, displayName: String) -> GameServer
}