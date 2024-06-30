package de.airblocks.teamfive.base.utils

import java.net.URL
import java.net.URLClassLoader

class CustomURLClassLoader(vararg urls: URL) : URLClassLoader(urls, ClassLoader.getSystemClassLoader()) {

    public override fun addURL(url: URL?) {
        super.addURL(url)
    }
}