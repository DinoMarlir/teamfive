plugins {
    alias(libs.plugins.jvm)
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(project(":base"))
    compileOnly(libs.minestom)
}