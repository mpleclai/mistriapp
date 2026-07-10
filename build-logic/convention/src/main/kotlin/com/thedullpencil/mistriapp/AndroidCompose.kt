package com.thedullpencil.mistriapp

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(commonExtension: ApplicationExtension) {
    commonExtension.apply {
        buildFeatures { compose = true }
        testOptions.unitTests.isIncludeAndroidResources = true
    }

    configureComposeDependencies()
}

internal fun Project.configureAndroidCompose(commonExtension: LibraryExtension) {
    commonExtension.apply {
        buildFeatures { compose = true }
        testOptions.unitTests.isIncludeAndroidResources = true
    }

    configureComposeDependencies()
}

private fun Project.configureComposeDependencies() {
    dependencies {
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))
        add("implementation", libs.findLibrary("androidx-compose-material-iconsExtended").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
        add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
    }
}
