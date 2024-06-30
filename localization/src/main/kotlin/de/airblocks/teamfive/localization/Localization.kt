package de.airblocks.teamfive.localization

import de.airblocks.teamfive.base.utils.DATA_FOLDER
import de.airblocks.teamfive.localization.configs.LocaleDataConfig
import java.io.File

class Localization {

    companion object {
        val INSTANCE = Localization()
        val LOCALIZATION_FOLDER: File get() {
            val file = File(DATA_FOLDER, "lang")

            if (!file.exists()) {
                file.mkdirs()
            }
            return file
        }
    }

    fun getValuesForLocale(namespace: String = "default", locale: String): HashMap<String, String> {
        return LocaleDataConfig(namespace, locale).get().entries
    }
}