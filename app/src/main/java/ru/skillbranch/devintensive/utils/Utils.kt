package ru.skillbranch.devintensive.utils

import android.service.voice.AlwaysOnHotwordDetector
import java.lang.StringBuilder
import java.util.*

object Utils {

    val dictionary: Map<String, String> = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )


    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0).let { if (it == "") null else it }
        val lastName = parts?.getOrNull(1).let { if (it == "") null else it }
        return (firstName to lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val sb = StringBuilder()
        val locale = Locale("ru")
        for (i in payload.indices) {
            val symbol = payload.subSequence(i, i + 1).toString()
            sb.append(
                when {
                    !dictionary.containsKey(symbol) && dictionary.containsKey(symbol.toLowerCase()) ->
                        dictionary[symbol.toLowerCase()]?.capitalize()
                    dictionary.containsKey(symbol) -> dictionary[symbol]
                    symbol == " " -> divider
                    else -> symbol
                }
            )
        }
        return sb.toString()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val locale = Locale("ru")
        return ((if (firstName.isNullOrBlank()) "" else firstName.subSequence(0,1).toString().toUpperCase(locale)) +
                if (lastName.isNullOrBlank()) "" else lastName.subSequence(0,1).toString().toUpperCase(locale)).let {
                    if (it == "") "null" else it
                }
    }

}