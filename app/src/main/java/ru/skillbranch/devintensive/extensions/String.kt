package ru.skillbranch.devintensive.extensions

fun String.truncate(len: Int = 16): String {
    val trim = trimEnd()
    if (trim.length <= len) {
        return trim
    }
    return "${substring(0, len).trimEnd()}..."
}