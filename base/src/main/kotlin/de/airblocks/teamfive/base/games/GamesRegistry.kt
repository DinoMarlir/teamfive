package de.airblocks.teamfive.base.games

object GamesRegistry {
    private val games: MutableMap<String, AbstractGameMode> = mutableMapOf()

    fun register(gameMode: AbstractGameMode) {
        games[gameMode.name.toString()] = gameMode
    }

    fun getGameMode(name: String): AbstractGameMode? {
        return games[name]
    }

    fun getAllGameModes(): List<AbstractGameMode> {
        return games.values.toList()
    }

    fun unregister(gameMode: AbstractGameMode) {
        games.remove(gameMode.name.toString())
    }

    fun unregister(name: String) {
        games.remove(name)
    }
}