package de.airblocks.teamfive.base.player.permissions.group

import de.airblocks.teamfive.base.utils.AbstractCachedConfig
import de.airblocks.teamfive.base.utils.DATA_FOLDER
import java.io.File
import java.nio.file.Path

object PermissionGroupRepository {
    private val groupCache = mutableMapOf<String, AbstractCachedConfig<PermissionGroup>>()

    fun getRegisteredGroupOrPut(id: String): AbstractCachedConfig<PermissionGroup> {
        val first = groupCache[id] ?: SerializablePermissionGroup(id).also { groupCache[id] = it }
        return first
    }

    fun getGroupOrCreate(id: String): PermissionGroup {
        return getRegisteredGroupOrPut(id).get()
    }

    fun saveGroup(id: String, group: PermissionGroup = getGroupOrCreate(id)) {
        val registeredGroupOrPut = getRegisteredGroupOrPut(id)
        registeredGroupOrPut.set(group)
        registeredGroupOrPut.push()
    }

    fun getAllGroups(cached: Boolean = false): List<PermissionGroup> {
        if (cached) return groupCache.values.map { it.get() }

        return File(DATA_FOLDER, "permission/groups").listFiles()?.map { file ->
            val id = file.nameWithoutExtension
            getGroupOrCreate(id)
        } ?: emptyList()
    }

    class SerializablePermissionGroup(id: String): AbstractCachedConfig<PermissionGroup>(
        Path.of(DATA_FOLDER.toPath().toString(), "permission", "groups", "$id.json"),
        PermissionGroup.Builder(id).build(),
        PermissionGroup.serializer(),
        PermissionGroup.serializer()
    )
}