buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

// Provides the official plugin classes that the convention plugins apply by id.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}


