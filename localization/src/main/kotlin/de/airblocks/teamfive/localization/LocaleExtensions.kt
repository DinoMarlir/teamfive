package de.airblocks.teamfive.localization

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

fun String.compileMiniMessage(): Component {
    return MiniMessage.miniMessage().deserialize(this)
}