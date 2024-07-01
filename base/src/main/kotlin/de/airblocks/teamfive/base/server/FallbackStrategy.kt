package de.airblocks.teamfive.base.server

import net.minestom.server.event.player.AsyncPlayerConfigurationEvent

// TODO: implement fallback if a server stops
abstract class FallbackStrategy {
    abstract fun asyncPlayerConfigurationEvent(event: AsyncPlayerConfigurationEvent)

    class Fallback(
        private val callback: (AsyncPlayerConfigurationEvent) -> Unit
    ): FallbackStrategy() {

        override fun asyncPlayerConfigurationEvent(event: AsyncPlayerConfigurationEvent) {
            callback.invoke(event)
        }
    }

    object NoFallback: FallbackStrategy() {

        override fun asyncPlayerConfigurationEvent(event: AsyncPlayerConfigurationEvent) = Unit
    }
}