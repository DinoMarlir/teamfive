package de.airblocks.teamfive.skywars.team

import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.skywars.SkyWarsGame
import de.airblocks.teamfive.skywars.team.exception.TeamFullException

class Team(val number: Int) {
    val MAX_PLAYERS = SkyWarsGame.instance.maxPlayersInTeam
    private val players = mutableListOf<GamePlayer>()

    fun addPlayer(player: GamePlayer) {
        if (players.size >= MAX_PLAYERS) {
            throw TeamFullException()
        }
        players.add(player)
    }

    fun removePlayer(player: GamePlayer) {
        players.remove(player)
    }

    fun isFull(): Boolean {
        return players.size >= MAX_PLAYERS
    }

    fun getPlayers(): List<GamePlayer> {
        return players
    }
}