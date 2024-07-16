package de.airblocks.teamfive.base.player

import de.airblocks.teamfive.base.serializer.UUIDSerializer
import de.airblocks.teamfive.base.utils.AbstractCachedConfig
import de.airblocks.teamfive.base.utils.DATA_FOLDER
import kotlinx.serialization.Serializable
import java.io.File
import java.util.*

@Serializable
data class SerializableGamePlayerModel(
    @Serializable(with = UUIDSerializer::class) val uuid: UUID,
    var username: String?,
    var permissions: HashMap<String, Boolean>,
    var groups: List<String> = listOf()
)

val PLAYER_FOLDER = File(DATA_FOLDER, "players")

class SerializableGamePlayer(uuid: UUID, username: String?): AbstractCachedConfig<SerializableGamePlayerModel>(
    path = File(PLAYER_FOLDER, "$uuid.json").toPath(),
    default = SerializableGamePlayerModel(uuid, username, hashMapOf()),
    serializer = SerializableGamePlayerModel.serializer(),
    deserializer = SerializableGamePlayerModel.serializer()
)