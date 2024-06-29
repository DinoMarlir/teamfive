package de.airblocks.teamfive.base.component

abstract class BaseComponent {
    open fun enable() = Unit
    open fun disable() = Unit
}