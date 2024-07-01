package de.airblocks.teamfive.skywars

import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.GameServer
import de.airblocks.teamfive.skywars.team.TeamProvider
import de.airblocks.teamfive.skywars.team.exception.TeamFullException

class SkyWarsServer(id: String, displayName: String): GameServer(id, displayName) {

    override fun enable() {
        println("yoo")
        for (i in 1..SkyWarsGame.instance.teams) {
            println("creating team $i")
            TeamProvider.createTeam(i)
        }
    }

    override fun disable() {
    }

    override fun initializePlayer(player: GamePlayer) {
        val nextFreeTeam = TeamProvider.getNextFreeTeam()

        if (nextFreeTeam == null) {
            player.kick("Internal error: No free team available.")
            return
        }

        try {
            nextFreeTeam.addPlayer(player)
        } catch (e: TeamFullException) {
            player.kick("Internal error: Team is full.")
            return
        }
    }

    override fun uninitializePlayer(player: GamePlayer) {
    }
}