package de.airblocks.teamfive.branding

import de.airblocks.teamfive.base.utils.AbstractCachedConfig
import de.airblocks.teamfive.base.utils.CONFIG_FOLDER
import kotlinx.serialization.Serializable
import java.nio.file.Path

@Serializable
data class BrandingConfigModel(
    val maxPlayers: Int,
    val motdHeader: String,
    val motdFooter: String
)

object BrandingConfig: AbstractCachedConfig<BrandingConfigModel>(
    path = Path.of(CONFIG_FOLDER.path.toString(), "branding.json"),
    default = BrandingConfigModel(
        maxPlayers = 50,
        motdHeader = "<red>Welcome to TeamFive!",
        motdFooter = "<rainbow>Have fun!"
    ),
    serializer = BrandingConfigModel.serializer(),
    deserializer = BrandingConfigModel.serializer()
)

val config = BrandingConfig.get()