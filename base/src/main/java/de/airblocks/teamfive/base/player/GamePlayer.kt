package de.airblocks.teamfive.base.player

import de.airblocks.teamfive.base.server.GameServer
import de.airblocks.teamfive.base.server.GameServerFactory
import de.airblocks.teamfive.base.server.exception.GameServerNotExistsException
import net.minestom.server.entity.Player
import net.minestom.server.network.player.PlayerConnection
import java.util.*

class GamePlayer(uuid: UUID, username: String, playerConnection: PlayerConnection): Player(uuid, username, playerConnection) {

    fun sendToServer(serverName: String) {
        sendToServer(GameServerFactory.getServerByName(serverName) ?: throw GameServerNotExistsException())
    }

    fun sendToServer(server: GameServer) {
        // TODO: Implement this method to send the player to the specified server
    }
}