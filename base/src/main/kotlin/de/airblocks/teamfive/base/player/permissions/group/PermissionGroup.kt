package de.airblocks.teamfive.base.player.permissions.group

import de.airblocks.teamfive.base.player.permissions.group.PermissionGroup.Companion.permissionGroup
import kotlinx.serialization.Serializable

@Serializable
data class PermissionGroup(
    var id: String,
    var name: String,
    var permissions: Map<String, Boolean>,
    private var dependencies: List<String>,
    var priority: Int,
    var default: Boolean,
    var prefix: String?,
    var suffix: String?,
    var color: String?
) {

    companion object {
        fun permissionGroup(id: String, builder: PermissionGroup.Builder.() -> Unit) = PermissionGroup.Builder(id).apply(builder).build()
    }

    fun update() {
        val groupOrCreate = PermissionGroupRepository.getGroupOrCreate(id)

        groupOrCreate.let { group ->
            group.name = name
            group.permissions = permissions
            group.dependencies = dependencies
            group.priority = priority
            group.default = default
            group.prefix = prefix
            group.suffix = suffix
            group.color = color
        }

        PermissionGroupRepository.saveGroup(id, groupOrCreate)
    }

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