package de.airblocks.teamfive.lobby.listener

import de.airblocks.teamfive.lobby.LobbyServer.Companion.INSTANCE

fun initializeListener() {
    INSTANCE.eventNode().addListener(PlayerBlockInteractListener.PLAYER_BLOCK_INTERACT_LISTENER)

}