package de.airblocks.teamfive.base.server

import de.airblocks.teamfive.base.player.GamePlayer
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.GenerationUnit
import java.util.*

// TODO: remove
class TestServerImpl: GameServer(UUID.randomUUID().toString(), "Lobby") {

    override fun enable() = Unit

    override fun disable() = Unit

    override fun initializePlayer(player: GamePlayer) {

    }

    override fun uninitializePlayer(player: GamePlayer) {
        
    }

    override val fallbackStrategy = FallbackStrategy.Fallback { event ->
        val instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer()


        // Set the ChunkGenerator
        instanceContainer.setGenerator { unit: GenerationUnit ->
            unit.modifier().fillHeight(0, 40, Block.SLIME_BLOCK)
        }
        val player: GamePlayer = event.player as GamePlayer
        event.spawningInstance = instanceContainer
        player.respawnPoint = Pos(0.0, 42.0, 0.0)
    }
}