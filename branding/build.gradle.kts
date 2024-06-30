plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.serialization)
    alias(libs.plugins.shadow)
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(project(":base"))
    compileOnly(libs.minestom)
    compileOnly(libs.bundles.serialization)
    implementation(libs.bundles.kyori)
}

/*
tasks.shadowJar {
    fun reloc(packageName: String) {
        relocate(packageName, "de.airblocks.teamfive.branding.dependency.$packageName")
    }

    reloc("net.kyori")
}
 */

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}