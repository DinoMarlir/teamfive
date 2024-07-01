package de.airblocks.teamfive.skywars

import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.GameServer
import de.airblocks.teamfive.skywars.team.TeamProvider

class SkyWarsServer(id: String, displayName: String): GameServer(id, displayName) {

    override fun enable() {
        for (i in 1..SkyWarsGame.instance.teams) {
            TeamProvider.createTeam(i)
        }
    }

    override fun disable() {
    }

    override fun initializePlayer(player: GamePlayer) {
    }

    override fun uninitializePlayer(player: GamePlayer) {
    }
}