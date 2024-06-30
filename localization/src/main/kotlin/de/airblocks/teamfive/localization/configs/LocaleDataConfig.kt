package de.airblocks.teamfive.localization.configs

import de.airblocks.teamfive.base.utils.AbstractCachedConfig
import de.airblocks.teamfive.localization.Localization.Companion.LOCALIZATION_FOLDER
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class LocaleDataModel(
    val localeName: String,
    val entries: HashMap<String, String>
)

class LocaleDataConfig(namespace: String, locale: String, default: HashMap<String, String> = hashMapOf()): AbstractCachedConfig<LocaleDataModel>(
    path = File(LOCALIZATION_FOLDER, "$namespace/$locale.json").toPath(),
    default = LocaleDataModel(locale, default),
    serializer = LocaleDataModel.serializer(),
    deserializer = LocaleDataModel.serializer()
)