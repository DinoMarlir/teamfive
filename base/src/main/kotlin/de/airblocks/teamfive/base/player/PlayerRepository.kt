package de.airblocks.teamfive.base.player

import de.airblocks.teamfive.base.utils.AbstractCachedConfig
import java.util.*

object PlayerRepository {
    private val playerCache: MutableMap<UUID, AbstractCachedConfig<SerializableGamePlayerModel>> = mutableMapOf()

    fun getRegisteredPlayerOrPut(uuid: UUID): AbstractCachedConfig<SerializableGamePlayerModel> {
        val first = playerCache[uuid] ?: SerializableGamePlayer(uuid, null).also { playerCache[uuid] = it }
        return first
    }

    fun getPlayerOrCreate(uuid: UUID): SerializableGamePlayerModel {
        return getRegisteredPlayerOrPut(uuid).get()
    }

    fun savePlayer(uuid: UUID, player: SerializableGamePlayerModel = getPlayerOrCreate(uuid)) {
        val registeredPlayerOrPut = getRegisteredPlayerOrPut(uuid)
        registeredPlayerOrPut.set(player)
        registeredPlayerOrPut.push()
    }

    fun saveAll() {
        playerCache.forEach { (_, player) -> player.push() }
    }
}