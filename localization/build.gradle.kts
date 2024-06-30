plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.serialization)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":base"))
    implementation(libs.bundles.serialization)
    implementation(libs.bundles.kyori)
}