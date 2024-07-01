package de.airblocks.teamfive.skywars

import de.airblocks.teamfive.base.games.AbstractGameMode
import de.airblocks.teamfive.base.server.GameServer
import net.kyori.adventure.text.Component

class SkyWarsGame(
    val teams: Int,
    val maxPlayersInTeam: Int,
    override val minPlayersToStart: Int = maxPlayersInTeam * 2
) : AbstractGameMode() {
    override val simpleName: String = "SkyWars-${teams}x${maxPlayersInTeam}"
    override val name: Component = Component.text(simpleName)
    override val description: Component = Component.text("The SkyWars Game")
    override val gameServer: (id: String, displayName: String) -> GameServer = { id, displayName ->
        SkyWarsServer(id, displayName)
    }
    override val maxPlayers: Int = maxPlayersInTeam * teams

    companion object {
        lateinit var instance: SkyWarsGame
    }

    init {
        instance = this
    }
}