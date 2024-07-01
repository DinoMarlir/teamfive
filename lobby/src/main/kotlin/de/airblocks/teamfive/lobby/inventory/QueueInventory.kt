package de.airblocks.teamfive.lobby.inventory

import de.airblocks.teamfive.lobby.LobbyServer
import de.airblocks.teamfive.lobby.queue.Queue
import de.airblocks.teamfive.lobby.queue.exception.PlayerAlreadyInQueueException
import de.airblocks.teamfive.lobby.queue.exception.QueueFullException
import de.airblocks.teamfive.lobby.utils.currentQueue
import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import net.minestom.server.event.EventListener
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.tag.Tag
import net.minestom.server.timer.Task
import net.minestom.server.timer.TaskSchedule

class QueueInventory: Inventory(InventoryType.CHEST_4_ROW, "Queue") {

    val INVENTORY_CLICK_LISTENER = EventListener.of(InventoryPreClickEvent::class.java) { event ->
        val player = event.player
        val item = event.clickedItem

        val firstOrNull = LobbyServer.queues.values.firstOrNull {
            item.getTag(Tag.String("queue_name")) == it.name.toString()
        } ?: return@of

        try {
            if (firstOrNull != player.currentQueue()) player.currentQueue()?.removePlayer(player)
            firstOrNull.addPlayer(player)
        } catch (e: QueueFullException) {
            player.sendMessage("Queue is full")
        } catch (e: PlayerAlreadyInQueueException) {
            player.currentQueue()?.removePlayer(player)
        }

        event.isCancelled = true
    }

    val playerSchedeulerMap: MutableMap<Player, Task> = mutableMapOf()

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

        title = buildTitleForPlayer(player)

        val task = MinecraftServer.getSchedulerManager().submitTask {
            title = buildTitleForPlayer(player)
            return@submitTask TaskSchedule.seconds(1)
        }

        playerSchedeulerMap[player] = task

        return super.addViewer(player)
    }

    override fun removeViewer(player: Player): Boolean {
        with(player.eventNode()) {
            removeListener(INVENTORY_CLICK_LISTENER)
        }

        playerSchedeulerMap[player]?.cancel()
        playerSchedeulerMap.remove(player)

        return super.removeViewer(player)
    }

    private fun generateItemStackForQueue(queue: Queue<*>): ItemStack {
        return ItemStack.of(queue.gameMode.displayMaterial).with {
            it.customName(queue.name)
            it.setTag(Tag.String("queue_name"), queue.name.toString())
        }
    }

    private fun buildTitleForPlayer(player: Player): Component {
        return Component.text("Queue: ").append(player.currentQueue()?.name ?: Component.text("none"))
    }
}