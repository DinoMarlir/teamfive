plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.serialization)
    alias(libs.plugins.shadow)
    `maven-publish`
}

repositories {
    mavenCentral()

    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/OWNER/REPO")
        credentials {
            username = project.findProperty("gpr.user") as String?
            password = project.findProperty("gpr.token") as String?
        }
    }
}

dependencies {
    implementation(libs.minestom)
    compileOnly(libs.bundles.serialization)
    compileOnly(libs.coroutines)
    implementation(libs.bundles.ktor)
    compileOnly(libs.bundles.kyori)
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

publishing {
    publications {
        create<MavenPublication>("baseGitHub") {
            from(components["java"])

            groupId = groupId
            artifactId = "base"
            version = version

            pom {
                name.set("Base")
            }
        }
    }
}