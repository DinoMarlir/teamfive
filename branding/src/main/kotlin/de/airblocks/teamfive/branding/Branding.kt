package de.airblocks.teamfive.branding

import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.MinecraftServer
import net.minestom.server.event.server.ServerListPingEvent

class Branding {
    private val miniMessage = MiniMessage.miniMessage()

    fun apply() {
        MinecraftServer.getGlobalEventHandler().addListener(ServerListPingEvent::class.java) {

            with(it.responseData) {
                maxPlayer = config.maxPlayers
                description = miniMessage
                    .deserialize(config.motdHeader)
                    .appendNewline()
                    .append(
                        miniMessage.deserialize(config.motdFooter)
                    )
            }
        }
    }
}