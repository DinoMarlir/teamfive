package de.airblocks.teamfive.dummygame

import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.GameServer

class DummyGameServer(id: String, displayName: String): GameServer(id, displayName) {

    override fun enable() {
    }

    override fun disable() {
    }

    override fun initializePlayer(player: GamePlayer) {
    }

    override fun uninitializePlayer(player: GamePlayer) {
    }
}