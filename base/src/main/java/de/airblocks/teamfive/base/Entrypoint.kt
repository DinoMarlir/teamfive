package de.airblocks.teamfive.base

import de.airblocks.teamfive.base.models.MinecraftServerConfigModel
import de.airblocks.teamfive.base.utils.AbstractCachedConfig
import net.minestom.server.MinecraftServer
import java.nio.file.Path

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
    minecraftServer.start(config.ip, config.port)
}