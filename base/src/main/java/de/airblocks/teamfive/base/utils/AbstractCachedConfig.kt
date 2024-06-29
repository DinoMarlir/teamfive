package de.airblocks.teamfive.base.utils

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import java.io.File
import java.nio.file.Path

abstract class AbstractCachedConfig<V>(
    private val path: Path,
    private val default: V,
    private val serializer: SerializationStrategy<V>,
    private val deserializer: DeserializationStrategy<V>
) {
    private var cachedData: V? = null
    private val file: File get() {
        val toFile = path.toFile()

        if (!toFile.exists()) {
            toFile.writeText(JSON.encodeToString(serializer, default))
        }

        return toFile
    }

    fun getCachedData(): V? {
        return cachedData
    }

    fun get(cached: Boolean = true): V {
        if (cached && cachedData != null) return cachedData!!
        val text = file.readText()
        val deserializedData = JSON.decodeFromString(deserializer, text)

        cachedData = deserializedData
        return cachedData!!
    }

    fun set(value: V, write: Boolean = false): V {
        cachedData = value

        if (write) file.writeText(JSON.encodeToString(serializer, value))
        return value
    }

    fun push(): V {
        file.writeText(JSON.encodeToString(serializer, cachedData ?: return default))
        return cachedData ?: default
    }
}