package de.airblocks.teamfive.base

import de.airblocks.teamfive.base.commands.ServerCommand
import de.airblocks.teamfive.base.commands.TestCommand
import de.airblocks.teamfive.base.component.ComponentLoader
import de.airblocks.teamfive.base.dependency.DependencyHandler
import de.airblocks.teamfive.base.models.MinecraftServerConfigModel
import de.airblocks.teamfive.base.player.GamePlayer
import de.airblocks.teamfive.base.player.PlayerHandler
import de.airblocks.teamfive.base.player.permissions.group.PermissionGroupRepository
import de.airblocks.teamfive.base.server.GameServerFactory
import de.airblocks.teamfive.base.utils.AbstractCachedConfig
import de.airblocks.teamfive.base.utils.initFolders
import net.minestom.server.MinecraftServer
import net.minestom.server.extras.MojangAuth
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
    PlayerHandler.init()

    listOf(ServerCommand, TestCommand).forEach {
        MinecraftServer.getCommandManager().register(it)
    }
    ComponentLoader.INSTANCE.initialize()
    minecraftServer.start(config.ip, config.port)
}

fun preStart() {
    DependencyHandler
    initFolders()
    if (config.enableMojangAuth) MojangAuth.init()

    MinecraftServer.setBrandName("TeamFive-Base")

    MinecraftServer.getConnectionManager().setPlayerProvider { uuid: UUID, username: String, playerConnection: PlayerConnection ->
        GamePlayer(uuid, username, playerConnection)
    }

    GameServerFactory.getAllServers().forEach { println(it.displayName) }
}