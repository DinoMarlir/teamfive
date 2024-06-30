package de.airblocks.teamfive.lobby.utils

import net.kyori.adventure.text.Component
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

val QUEUE_ITEM = ItemStack
    .of(Material.RECOVERY_COMPASS)
    .withCustomName(Component.text("Queues"))