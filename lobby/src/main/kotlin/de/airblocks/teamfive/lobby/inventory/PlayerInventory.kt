package de.airblocks.teamfive.lobby.inventory

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

fun updateInventoryForPlayer(player: Player) {
    with(player.inventory) {
        clear()
        setItemStack(4, ItemStack
            .of(Material.RECOVERY_COMPASS)
            .withCustomName(Component.text("Queues"))
        )
    }
}