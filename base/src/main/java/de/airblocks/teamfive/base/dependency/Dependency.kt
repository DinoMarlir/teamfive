package de.airblocks.teamfive.base.dependency

import kotlinx.serialization.Serializable
import java.io.File

@Serializable
class Dependency(
    private var groupId: String,
    var artifactId: String,
    var version: String,
    private val classifier: String = "",
    val repository: String = "https://repo.maven.apache.org/maven2/"
) {

    fun exists(): Boolean {
        return File("dependencies/$artifactId-$version.jar").exists()
    }

    override fun toString(): String {
        return "$repository${groupId.replace('.', '/')}/${artifactId}/${version}/"
    }

    fun fileName(): String {
        return "$artifactId-$version${getClassifier()}.jar"
    }

    fun getClassifier() : String {
        return if(classifier.isEmpty()) "" else "-$classifier"
    }
}