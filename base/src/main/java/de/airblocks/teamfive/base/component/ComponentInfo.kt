package de.airblocks.teamfive.base.component

import kotlinx.serialization.Serializable

@Serializable
data class ComponentInfo(
    val name: String,
    val authors: List<String>,
    val version: String,
    val mainClass: String
)
