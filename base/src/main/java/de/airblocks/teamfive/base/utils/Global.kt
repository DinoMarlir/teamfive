package de.airblocks.teamfive.base.utils

import kotlinx.serialization.json.Json

val JSON = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
}