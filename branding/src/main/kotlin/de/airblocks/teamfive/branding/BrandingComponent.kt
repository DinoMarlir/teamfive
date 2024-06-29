package de.airblocks.teamfive.branding

import de.airblocks.teamfive.base.component.BaseComponent
import de.airblocks.teamfive.base.dependency.Dependency
import de.airblocks.teamfive.base.dependency.DependencyHandler

class BrandingComponent: BaseComponent() {

    override fun enable() {
        DependencyHandler.load(Dependency("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.6.3"))
        Branding().apply()
    }
}