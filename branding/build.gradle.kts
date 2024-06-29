plugins {
    alias(libs.plugins.jvm)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":base"))
    implementation(libs.minestom)
}