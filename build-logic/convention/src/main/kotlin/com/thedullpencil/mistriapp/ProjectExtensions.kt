package com.thedullpencil.mistriapp

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.androidNamespace(): String =
    if (name == "app") {
        "com.thedullpencil.mistriapp"
    } else {
        "com.thedullpencil" + path.replace(":", ".").lowercase()
    }

internal fun calculateResourcePrefix(path: String): String {

    return path.split("""\W""".toRegex()).drop(1).distinct()
        .joinToString(separator = "_").lowercase() + "_"
}
