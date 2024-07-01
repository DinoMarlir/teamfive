package de.airblocks.teamfive.lobby.listener

import de.airblocks.teamfive.lobby.LobbyServer.Companion.INSTANCE

fun initializeListener() {
    with(INSTANCE.eventNode()) {
        addListener(PlayerBlockInteractListener.PLAYER_BLOCK_INTERACT_LISTENER)
        addListener(PlayerSpawnListener.PLAYER_SPAWN_LISTENER)
        addListener(PlayerDisconnectListener.PLAYER_DISCONNECT_LISTENER)
    }
}