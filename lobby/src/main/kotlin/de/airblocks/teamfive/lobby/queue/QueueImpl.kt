package de.airblocks.teamfive.lobby.queue

import de.airblocks.teamfive.base.games.AbstractGameMode

class QueueImpl<G: AbstractGameMode>(override val maxPlayers: Int, override val minPlayersToStart: Int) : Queue<G>()