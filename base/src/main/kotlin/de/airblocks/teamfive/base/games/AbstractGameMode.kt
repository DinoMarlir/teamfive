package de.airblocks.teamfive.base.games

import de.airblocks.teamfive.base.server.GameServer
import net.kyori.adventure.text.Component
import net.minestom.server.item.Material

abstract class AbstractGameMode {
    abstract val name: Component
    abstract val simpleName: String
    abstract val description: Component

    abstract val gameServer: (id: String, displayName: String) -> GameServer

    abstract val minPlayersToStart: Int
    abstract val maxPlayers: Int

    open val displayMaterial: Material = Material.GRASS_BLOCK
}