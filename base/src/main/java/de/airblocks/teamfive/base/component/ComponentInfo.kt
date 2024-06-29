package de.airblocks.teamfive.base.component

import de.airblocks.teamfive.base.dependency.Dependency
import kotlinx.serialization.Serializable

@Serializable
data class ComponentInfo(
    val name: String,
    val authors: List<String>,
    val version: String,
    val mainClass: String,
    val libraries: List<Dependency> = listOf()
)
