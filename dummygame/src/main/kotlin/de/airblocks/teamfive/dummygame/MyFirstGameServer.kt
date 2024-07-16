package de.airblocks.teamfive.dummygame

import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.FallbackStrategy
import de.airblocks.teamfive.base.server.GameServer

class MyFirstGameServer(
    id: String,
    displayName: String
): GameServer(id, displayName) {

    override fun enable() {
        TODO("Not yet implemented")
    }

    override fun disable() {
        TODO("Not yet implemented")
    }

    override fun initializePlayer(player: GamePlayer) {
        TODO("Not yet implemented")
    }

    override fun uninitializePlayer(player: GamePlayer) {
        TODO("Not yet implemented")
    }

}