package de.airblocks.teamfive.base.server

import de.airblocks.teamfive.base.server.exception.GameServerNotExistsException

/**
 * This class is a factory for managing game servers.
 * It provides methods for registering, starting, stopping servers and getting server by its name.
 * It also provides a method to stop all running servers.
 */
object GameServerFactory {

    /**
     * A map to store all registered servers. The key is the display name of the server.
     */
    private val servers: MutableMap<String, GameServer> = mutableMapOf()

    /**
     * Registers a new server.
     *
     * @param server The server to be registered.
     */
    fun registerServer(server: GameServer) {
        servers[server.displayName] = server
    }

    /**
     * Returns a server by its name.
     *
     * @param name The name of the server.
     * @return The server with the given name, or null if no such server exists.
     */
    fun getServerByName(name: String): GameServer? {
        return servers[name]
    }

    /**
     * Returns a server by its name. Throws an exception if no such server exists.
     *
     * @param name The name of the server.
     * @return The server with the given name.
     * @throws GameServerNotExistsException If no server with the given name exists.
     */
    fun getServerByNameOrThrow(name: String): GameServer {
        return servers[name] ?: throw GameServerNotExistsException()
    }

    /**
     * Starts a server.
     *
     * @param server The server to be started.
     */
    fun startServer(server: GameServer) {
        server.start()
    }

    /**
     * Stops a server and removes it from the registered servers.
     *
     * @param server The server to be stopped.
     */
    fun stopServer(server: GameServer) {
        server.stop {
            servers.remove(server.displayName)
        }
    }

    /**
     * Stops all running servers and removes them from the registered servers.
     */
    fun stopAll() {
        servers.values.forEach {
            it.stop {
                servers.remove(it.displayName)
            }
        }
    }

    /**
     * Returns a list of all registered servers.
     *
     * @return A list of all registered servers.
     */
    fun getAllServers(): List<GameServer> {
        return servers.values.toList()
    }
}