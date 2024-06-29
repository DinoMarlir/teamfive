package de.airblocks.teamfive.base.server.instance

import net.kyori.adventure.text.Component
import net.minestom.server.instance.Instance

class GameServerInstanceFactory {

    val instances: MutableMap<String, Instance> = mutableMapOf()

    fun registerInstance(instance: Instance) {
        instances[instance.uniqueId.toString()] = instance
    }

    fun getInstanceById(id: String): Instance? {
        return instances[id]
    }
}