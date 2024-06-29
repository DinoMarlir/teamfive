package de.airblocks.teamfive.base

import de.airblocks.teamfive.base.models.MinecraftServerConfigModel
import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.utils.AbstractCachedConfig
import de.airblocks.teamfive.base.utils.initFolders
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import net.minestom.server.extras.MojangAuth
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.GenerationUnit
import net.minestom.server.network.player.PlayerConnection
import java.nio.file.Path
import java.util.*

val minecraftServer: MinecraftServer = MinecraftServer.init()

private object MinecraftServerConfig: AbstractCachedConfig<MinecraftServerConfigModel>(
    path = Path.of("config.json"),
    default = MinecraftServerConfigModel(
        "0.0.0.0",
        25565
    ),
    serializer = MinecraftServerConfigModel.serializer(),
    deserializer = MinecraftServerConfigModel.serializer()
)

private val config = MinecraftServerConfig.get()

fun main() {
    preStart()
    testing()
    minecraftServer.start(config.ip, config.port)
}

fun preStart() {
    initFolders()
    if (config.enableMojangAuth) MojangAuth.init()

    MinecraftServer.getConnectionManager().setPlayerProvider { uuid: UUID, username: String, playerConnection: PlayerConnection ->
        GamePlayer(uuid, username, playerConnection)
    }
}

fun testing() {

    // Create the instance
    val instanceManager = MinecraftServer.getInstanceManager()
    val instanceContainer = instanceManager.createInstanceContainer()


    // Set the ChunkGenerator
    instanceContainer.setGenerator { unit: GenerationUnit ->
        unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK)
    }


    // Add an event callback to specify the spawning instance (and the spawn position)
    val globalEventHandler = MinecraftServer.getGlobalEventHandler()
    globalEventHandler.addListener(
        AsyncPlayerConfigurationEvent::class.java
    ) { event: AsyncPlayerConfigurationEvent ->
        val player: GamePlayer = event.player as GamePlayer
        event.spawningInstance = instanceContainer
        player.respawnPoint = Pos(0.0, 42.0, 0.0)
    }
}