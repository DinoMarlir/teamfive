package de.airblocks.teamfive.base.dependency

import de.airblocks.teamfive.base.utils.httpClient
import de.airblocks.teamfive.base.utils.mainScope
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.cio.*
import io.ktor.utils.io.*
import kotlinx.coroutines.launch
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path

object DependencyHandler {

    private val dependencyFolder = Path("libraries")
    private val loadedDependencies = ArrayList<Dependency>()

    init {
        if (!Files.exists(dependencyFolder)) {
            Files.createDirectory(dependencyFolder)
        }

        load(Dependency("net.minestom", "minestom-snapshots", "edb73f0a5a"))
        load(Dependency("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.6.3"))
        load(Dependency("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.9.0-RC"))
    }

    fun load(dependency: Dependency, callback: (Dependency) -> Unit = {}) {
        println("Loading dependency $dependency")
        this.download(dependency)
        this.addToClassPath(Path("libraries/${dependency.fileName()}"))
        callback.invoke(dependency)
    }

    fun getDependencies(): List<Dependency> {
        return this.loadedDependencies
    }


    private fun download(dependency: Dependency) {
        mainScope.launch {

            if (File(dependencyFolder.toFile(), dependency.fileName()).exists()) return@launch

            println("Downloading dependency $dependency")
            if (!dependency.exists()) {
                val url = "$dependency${dependency.artifactId}-${dependency.version}${dependency.getClassifier()}.jar"
                // val outputStream = FileOutputStream(File("dependencies/${dependency.fileName()}"))
                val outputFile = File("libraries/${dependency.fileName()}")

                val out: ByteReadChannel = httpClient.get {
                    url(url)
                    method = HttpMethod.Get
                }.bodyAsChannel()

                out.copyAndClose(outputFile.writeChannel())
            }
        }
    }
    class CustomURLClassLoader(vararg urls: URL) : URLClassLoader(urls, ClassLoader.getSystemClassLoader()) {

        public override fun addURL(url: URL?) {
            super.addURL(url)
        }
    }
    fun addToClassPath(path: Path) {
        CustomURLClassLoader(path.toUri().toURL()).addURL(path.toUri().toURL())
    }
}