package de.airblocks.teamfive.lobby

import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.server.FallbackStrategy
import de.airblocks.teamfive.base.server.GameServer
import de.airblocks.teamfive.base.server.GameServerFactory
import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.event.player.PlayerBlockInteractEvent
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.GenerationUnit
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import java.util.*

class LobbyServer(displayName: String): GameServer(UUID.randomUUID().toString(), displayName){

    override fun enable() {
        INSTANCE.setGenerator { unit: GenerationUnit ->
            unit.modifier().fillHeight(0, 40, Block.SLIME_BLOCK)
        }

        INSTANCE.eventNode().addListener(PlayerBlockInteractEvent::class.java) { event ->
            event.player.sendMessage("Hi")
            if (!event.player.itemInMainHand.isSimilar(ItemStack.of(Material.RECOVERY_COMPASS))) {
                return@addListener
            }

            val inventory: Inventory = Inventory(InventoryType.CHEST_3_ROW, "Servers")

            GameServerFactory.getAllServers().forEach {
                inventory.addItemStack(ItemStack.of(Material.SLIME_BLOCK).withCustomName(Component.text(it.displayName)))
            }

            event.player.openInventory(inventory)
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

        player.inventory.addItemStack(ItemStack.of(Material.RECOVERY_COMPASS))
    }

    val INSTANCE = MinecraftServer.getInstanceManager().createInstanceContainer()
}