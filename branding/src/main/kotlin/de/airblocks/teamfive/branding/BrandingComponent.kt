package de.airblocks.teamfive.branding

import de.airblocks.teamfive.base.component.BaseComponent
import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.event.server.ServerListPingEvent

class BrandingComponent: BaseComponent() {

    override fun enable() {
        MinecraftServer.getGlobalEventHandler().addListener(ServerListPingEvent::class.java) {
            it.responseData.maxPlayer = 50
            it.responseData.description = Component.text("Welcome to TeamFive!")
        }
    }
}