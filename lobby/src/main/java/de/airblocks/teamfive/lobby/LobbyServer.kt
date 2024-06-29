package de.airblocks.teamfive.lobby

import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.FallbackStrategy
import de.airblocks.teamfive.base.server.GameServer
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.GenerationUnit
import java.util.*

class LobbyServer(displayName: String): GameServer(UUID.randomUUID().toString(), displayName){

    override fun enable() {
        INSTANCE.setGenerator { unit: GenerationUnit ->
            unit.modifier().fillHeight(0, 40, Block.SLIME_BLOCK)
        }
    }

    override fun disable() {
    }

    override fun initializePlayer(player: GamePlayer) {
    }

    override fun uninitializePlayer(player: GamePlayer) {
    }

    override val fallbackStrategy = FallbackStrategy.Fallback { event ->
        val player: GamePlayer = event.player as GamePlayer
        event.spawningInstance = INSTANCE
        player.respawnPoint = Pos(0.0, 42.0, 0.0)
    }

    val INSTANCE = MinecraftServer.getInstanceManager().createInstanceContainer()
}