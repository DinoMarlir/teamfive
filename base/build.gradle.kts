plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.serialization)
    alias(libs.plugins.shadow)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.minestom)
    compileOnly(libs.bundles.serialization)
    compileOnly(libs.coroutines)
    implementation(libs.bundles.ktor)
}

tasks.shadowJar {
    /*
    fun reloc(packageName: String) {
        relocate(packageName, "de.airblocks.teamfive.base.dependency.$packageName")
    }

    reloc("io.ktor")
     */

    archiveFileName = "base.jar"
    manifest {
        attributes(
            mapOf(
                "Main-Class" to "de.airblocks.teamfive.base.EntrypointKt"
            )
        )
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}