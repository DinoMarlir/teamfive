/**
 * This class provides global utilities for the application.
 * It includes a JSON serializer with specific settings, and file references to important directories.
 */
package de.airblocks.teamfive.base.utils

import kotlinx.serialization.json.Json
import java.io.File

/**
 * A JSON serializer with specific settings.
 * It is configured to pretty print and ignore unknown keys.
 */
val JSON = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
}

/**
 * A reference to the root directory of the application.
 */
val ROOT_FOLDER = File(".")

/**
 * A reference to the data directory of the application.
 * This directory is used to store application data.
 */
val DATA_FOLDER = File(ROOT_FOLDER, "data")

/**
 * A reference to the configuration directory of the application.
 * This directory is used to store configuration files.
 */
val CONFIG_FOLDER = File(ROOT_FOLDER, "config")

/**
 * A reference to the logs directory of the application.
 * This directory is used to store log files.
 */
val LOG_FOLDER = File(ROOT_FOLDER, "logs")

/**
 * Initializes the directories used by the application.
 * If a directory does not exist, it is created.
 */
internal fun initFolders() {
    if (!DATA_FOLDER.exists()) DATA_FOLDER.mkdirs()
    if (!CONFIG_FOLDER.exists()) CONFIG_FOLDER.mkdirs()
    if (!LOG_FOLDER.exists()) LOG_FOLDER.mkdirs()
}