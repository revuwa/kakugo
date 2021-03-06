package org.kaqui

import android.content.Context
import android.os.Build
import androidx.preference.PreferenceManager

class LocaleManager {
    companion object {
        private var dictionaryLocale: String? = null

        fun updateDictionaryLocale(context: Context) {
            val forcedLocale = PreferenceManager.getDefaultSharedPreferences(context).getString("dictionary_language", "")!!
            val systemLocale =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                        context.resources.configuration.locales.getFirstMatch(arrayOf("en", "fr", "es"))?.language
                    else
                        context.resources.configuration.locale.language
            val finalLocale =
                    if (forcedLocale.isNotEmpty())
                        forcedLocale
                    else
                        systemLocale

            dictionaryLocale =
                    when (finalLocale) {
                        "fr", "es" -> finalLocale
                        else -> "en"
                    }
        }

        fun getDictionaryLocale(): String {
            return dictionaryLocale!!
        }

        fun isReady() = dictionaryLocale != null
    }
}