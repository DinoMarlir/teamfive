package de.airblocks.teamfive.base.server

object GameServerFactory {

    private val servers: MutableMap<String, GameServer> = mutableMapOf()

    fun registerServer(server: GameServer) {
        servers[server.name] = server
    }

    fun getServerByName(name: String): GameServer? {
        return servers[name]
    }

    fun startServer(server: GameServer) {
        server.start()
    }

    fun stopServer(server: GameServer) {
        server.stop {
            servers.remove(server.name)
        }
    }
    
    fun stopAll() {
        servers.values.forEach {
            it.stop {
                servers.remove(it.name)
            }
        }
    }

    fun getAllServers(): List<String> {
        return servers.keys.toList()
    }
}