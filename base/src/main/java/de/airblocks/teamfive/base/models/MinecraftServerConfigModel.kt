package de.airblocks.teamfive.base.models

import kotlinx.serialization.Serializable

@Serializable
data class MinecraftServerConfigModel(
    val ip: String,
    val port: Int,
    val enableMojangAuth: Boolean = true
)
