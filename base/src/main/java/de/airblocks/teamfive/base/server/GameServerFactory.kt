package de.airblocks.teamfive.base.server

import de.airblocks.teamfive.base.server.exception.GameServerNotExistsException

object GameServerFactory {

    private val servers: MutableMap<String, GameServer> = mutableMapOf()

    fun registerServer(server: GameServer) {
        servers[server.displayName] = server
    }

    fun getServerByName(name: String): GameServer? {
        return servers[name]
    }

    fun getServerByNameOrThrow(name: String): GameServer {
        return servers[name] ?: throw GameServerNotExistsException()
    }

    fun startServer(server: GameServer) {
        server.start()
    }

    fun stopServer(server: GameServer) {
        server.stop {
            servers.remove(server.displayName)
        }
    }

    fun stopAll() {
        servers.values.forEach {
            it.stop {
                servers.remove(it.displayName)
            }
        }
    }

    fun getAllServers(): List<String> {
        return servers.keys.toList()
    }
}