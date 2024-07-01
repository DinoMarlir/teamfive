package de.airblocks.teamfive.dummygame

import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.GameServer
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.GenerationUnit

class DummyGameServer(id: String, displayName: String): GameServer(id, displayName) {

    companion object {
        val INSTANCE = MinecraftServer.getInstanceManager().createInstanceContainer()
    }

    override fun enable() {
        INSTANCE.setGenerator { unit: GenerationUnit ->
            unit.modifier().fillHeight(0, 40, Block.STONE)
        }
    }

    override fun disable() {
    }

    override fun initializePlayer(player: GamePlayer) {
        player.instance = INSTANCE
        player.teleport(Pos(0.0, 42.0, 0.0))

        player.sendMessage("Welcome to the Dummy Game!")
    }

    override fun uninitializePlayer(player: GamePlayer) = Unit
}