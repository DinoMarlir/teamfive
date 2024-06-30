package de.airblocks.teamfive.lobby.inventory

import de.airblocks.teamfive.lobby.LobbyServer
import de.airblocks.teamfive.lobby.queue.Queue
import de.airblocks.teamfive.lobby.queue.exception.QueueFullException
import net.minestom.server.entity.Metadata
import net.minestom.server.entity.Player
import net.minestom.server.entity.metadata.display.ItemDisplayMeta
import net.minestom.server.event.EventListener
import net.minestom.server.event.inventory.InventoryClickEvent
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.tag.Tag

class QueueInventory: Inventory(InventoryType.CHEST_4_ROW, "Queue") {

    val INVENTORY_CLICK_LISTENER = EventListener.of(InventoryPreClickEvent::class.java) { event ->
        val player = event.player
        val item = event.clickedItem

        val firstOrNull = LobbyServer.queues.values.firstOrNull {
            item.getTag(Tag.String("queue_name")) == it.name.toString()
        } ?: return@of

        try {
            firstOrNull.addPlayer(player)
        } catch (e: QueueFullException) {
            player.sendMessage("Queue is full")
        }
    }

    init {
        for (i in 0..8) {
            setItemStack(i, ItemStack.of(Material.LIME_STAINED_GLASS_PANE))
        }
        for (i in 27..35) {
            setItemStack(i, ItemStack.of(Material.LIME_STAINED_GLASS_PANE))
        }

        LobbyServer.queues.forEach {
            addItemStack(generateItemStackForQueue(it.value))
        }
    }

    override fun addViewer(player: Player): Boolean {
        with(player.eventNode()) {
            addListener(INVENTORY_CLICK_LISTENER)

        }
        return super.addViewer(player)
    }

    override fun removeViewer(player: Player): Boolean {
        with(player.eventNode()) {
            removeListener(INVENTORY_CLICK_LISTENER)
        }
        return super.removeViewer(player)
    }

    private fun generateItemStackForQueue(queue: Queue<*>): ItemStack {
        return ItemStack.of(Material.PLAYER_HEAD).with {
            it.customName(queue.name)
            it.setTag(Tag.String("queue_name"), queue.name.toString())
        }
    }
}