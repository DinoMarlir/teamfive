package de.airblocks.teamfive.base.player

import de.airblocks.teamfive.base.exception.NoFallbackServerFoundException
import de.airblocks.teamfive.base.server.FallbackStrategy
import de.airblocks.teamfive.base.server.GameServerFactory
import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent

object PlayerHandler {

    fun init() {
        val globalEventHandler = MinecraftServer.getGlobalEventHandler()
        globalEventHandler.addListener(
            AsyncPlayerConfigurationEvent::class.java
        ) { event: AsyncPlayerConfigurationEvent ->
            val fallbackServer = GameServerFactory.getAllServers().firstOrNull { it.fallbackStrategy is FallbackStrategy.Fallback } ?: throw NoFallbackServerFoundException()

            fallbackServer.players[event.player.uuid] = event.player
            fallbackServer.fallbackStrategy.asyncPlayerConfigurationEvent(event)
        }
    }
}