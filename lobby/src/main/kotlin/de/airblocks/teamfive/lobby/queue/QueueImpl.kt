package de.airblocks.teamfive.lobby.queue

import de.airblocks.teamfive.base.games.AbstractGameMode
import net.kyori.adventure.text.Component

class QueueImpl<G: AbstractGameMode>(override val name: Component, override val maxPlayers: Int, override val minPlayersToStart: Int, gameMode: G) : Queue<G>(gameMode)