package de.airblocks.teamfive.base.player.permissions.group

import kotlinx.serialization.Serializable

@Serializable
data class PermissionGroup(
    val id: String,
    val name: String,
    val permissions: Map<String, Boolean>,
    private val dependencies: List<String>,
    val priority: Int,
    val default: Boolean,
    val prefix: String?,
    val suffix: String?,
    val color: String?
) {

    class Builder(id: String) {
        private var current = PermissionGroup(
            id,
            "",
            hashMapOf(),
            emptyList(),
            0,
            false,
            null,
            null,
            null
        )

        fun withName(name: String) = apply { current = current.copy(name = name) }
        fun withPermissions(permissions: Map<String, Boolean>) = apply { current = current.copy(permissions = permissions) }
        fun withDependencies(dependencies: List<String>) = apply { current = current.copy(dependencies = dependencies) }
        fun withPriority(priority: Int) = apply { current = current.copy(priority = priority) }
        fun withDefault(default: Boolean) = apply { current = current.copy(default = default) }
        fun withPrefix(prefix: String) = apply { current = current.copy(prefix = prefix) }
        fun withSuffix(suffix: String) = apply { current = current.copy(suffix = suffix) }
        fun withColor(color: String) = apply { current = current.copy(color = color) }
        fun build() = current
    }
}