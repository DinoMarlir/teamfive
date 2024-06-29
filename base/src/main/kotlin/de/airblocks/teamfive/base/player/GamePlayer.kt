package de.airblocks.teamfive.base.player

import de.airblocks.teamfive.base.server.GameServer
import de.airblocks.teamfive.base.server.GameServerFactory
import de.airblocks.teamfive.base.server.exception.GameServerNotExistsException
import net.minestom.server.entity.Player
import net.minestom.server.network.player.PlayerConnection
import java.util.*

/**
 * This class represents a game player.
 * It extends the Player class from Minestom and provides additional functionality.
 * It includes methods for sending the player to a specific game server.
 *
 * @param uuid The unique identifier of the player.
 * @param username The username of the player.
 * @param playerConnection The connection of the player.
 */
class GamePlayer(uuid: UUID, username: String, playerConnection: PlayerConnection): Player(uuid, username, playerConnection) {

    /**
     * Returns the current server that the player is connected to.
     * It iterates over all servers and returns the first one where the player is present.
     *
     * @return The current server of the player.
     * @throws NoSuchElementException If no server with the player exists.
     */
    fun currentServer(): GameServer {
        return GameServerFactory.getAllServers().first { it.players.containsKey(uuid) }
    }

    /**
     * Sends the player to a server with the given name.
     * If no server with the given name exists, a GameServerNotExistsException is thrown.
     *
     * @param serverName The name of the server.
     * @throws GameServerNotExistsException If no server with the given name exists.
     */
    fun sendToServer(serverName: String) {
        sendToServer(GameServerFactory.getServerByName(serverName) ?: throw GameServerNotExistsException())
    }

    /**
     * Sends the player to the specified server.
     * This method is not yet implemented.
     *
     * @param server The server to send the player to.
     */
    fun sendToServer(server: GameServer) {
        currentServer().uninitializePlayer(this)
        currentServer().players.remove(uuid)
        server.initializePlayer(this)
        server.players[uuid] = this
        // TODO: Implement this method to send the player to the specified server
    }
}