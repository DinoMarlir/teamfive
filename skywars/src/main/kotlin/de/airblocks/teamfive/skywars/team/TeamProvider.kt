package de.airblocks.teamfive.skywars.team

import de.airblocks.teamfive.base.player.GamePlayer

object TeamProvider {
    private val teams = mutableMapOf<Int, Team>()

    fun getTeam(number: Int): Team? {
        return teams[number]
    }

    fun createTeam(number: Int): Team {
        val team = Team(number)
        teams[number] = team
        return team
    }

    fun deleteTeam(number: Int) {
        teams.remove(number)
    }

    fun getTeams(): List<Team> {
        return teams.values.toList()
    }

    fun getTeamByPlayer(player: GamePlayer): Team? {
        return teams.values.find { it.getPlayers().contains(player) }
    }
}