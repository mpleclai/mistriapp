package com.thedullpencil.core.util

fun String.toDisplayName(): String =
    split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

fun List<String>.toStringList(): String =
    joinToString(", ") { it.toDisplayName() }
