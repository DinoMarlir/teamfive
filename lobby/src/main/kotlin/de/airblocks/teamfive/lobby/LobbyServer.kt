package de.airblocks.teamfive.lobby

import de.airblocks.teamfive.base.games.AbstractGameMode
import de.airblocks.teamfive.base.games.GamesRegistry
import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.FallbackStrategy
import de.airblocks.teamfive.base.server.GameServer
import de.airblocks.teamfive.lobby.listener.initializeListener
import de.airblocks.teamfive.lobby.queue.Queue
import de.airblocks.teamfive.lobby.queue.QueueImpl
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.GenerationUnit
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import java.util.*

class LobbyServer(displayName: String): GameServer(UUID.randomUUID().toString(), displayName) {

    companion object {
        private val _queues: MutableMap<AbstractGameMode, Queue<*>> = mutableMapOf()
        val queues: MutableMap<AbstractGameMode, Queue<*>> get() {
            _queues.putAll(
                GamesRegistry.getAllGameModes().map {
                    it to QueueImpl<AbstractGameMode>(name = it.name, maxPlayers = it.maxPlayers, minPlayersToStart = it.minPlayersToStart)
                }.toMap()
            )
            return _queues
        }
        val INSTANCE = MinecraftServer.getInstanceManager().createInstanceContainer()
    }

    override fun enable() {
        INSTANCE.setGenerator { unit: GenerationUnit ->
            unit.modifier().fillHeight(0, 40, Block.SLIME_BLOCK)
        }

        initializeListener()
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

        player.inventory.addItemStack(ItemStack.of(Material.RECOVERY_COMPASS))
    }

}