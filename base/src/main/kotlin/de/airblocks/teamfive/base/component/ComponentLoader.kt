package de.airblocks.teamfive.base.component

import de.airblocks.teamfive.base.dependency.DependencyHandler
import de.airblocks.teamfive.base.utils.COMPONENT_FOLDER
import de.airblocks.teamfive.base.utils.CustomURLClassLoader
import de.airblocks.teamfive.base.utils.JSON
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.jar.JarFile

class ComponentLoader {

    companion object {
        val INSTANCE = ComponentLoader()
    }

    fun initialize() {
        val dir = Paths.get(COMPONENT_FOLDER.path)
        Files.newDirectoryStream(dir, "*.jar").use { paths ->
            paths.forEach { path ->
                loadComponent(path)
            }
        }
    }

    fun loadComponent(path: Path) {
        val jarFile = JarFile(path.toFile())
        val jsonEntry = jarFile.getJarEntry("component.json")
        val componentInfo = jarFile.getInputStream(jsonEntry).reader().use { reader ->
            JSON.decodeFromString<ComponentInfo>(reader.readText())
        }

        componentInfo.libraries.forEach {
            DependencyHandler.load(it)
        }

        val classLoader = CustomURLClassLoader(path.toUri().toURL())
        classLoader.addURL(path.toUri().toURL())

        val clazz = Class.forName(componentInfo.mainClass, true, classLoader)
        val component = clazz.getDeclaredConstructor().newInstance() as BaseComponent

        println("starting component '${componentInfo.name}' by ${componentInfo.authors.joinToString(", ")}!")
        component.enable()
    }
}