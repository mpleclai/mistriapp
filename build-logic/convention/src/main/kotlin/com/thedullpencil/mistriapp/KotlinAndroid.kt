package com.thedullpencil.mistriapp

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

private const val ANDROID_COMPILE_SDK = 34
private const val ANDROID_MIN_SDK = 21
private val JvmCompatibility = JavaVersion.VERSION_11
private val KotlinJvmCompatibility = JvmTarget.JVM_11

internal fun Project.configureKotlinAndroid(commonExtension: ApplicationExtension) {
    configureAndroidDefaults(
        setCompileSdk = { commonExtension.compileSdk = it },
        setMinSdk = { commonExtension.defaultConfig { minSdk = it } },
        configureCompileOptions = {
            commonExtension.compileOptions {
                sourceCompatibility = JvmCompatibility
                targetCompatibility = JvmCompatibility
                isCoreLibraryDesugaringEnabled = true
            }
        },
    )

    configureKotlinAndroidProject()
}

internal fun Project.configureKotlinAndroid(commonExtension: LibraryExtension) {
    configureAndroidDefaults(
        setCompileSdk = { commonExtension.compileSdk = it },
        setMinSdk = { commonExtension.defaultConfig { minSdk = it } },
        configureCompileOptions = {
            commonExtension.compileOptions {
                sourceCompatibility = JvmCompatibility
                targetCompatibility = JvmCompatibility
                isCoreLibraryDesugaringEnabled = true
            }
        },
    )

    configureKotlinAndroidProject()
}

private fun Project.configureAndroidDefaults(
    setCompileSdk: (Int) -> Unit,
    setMinSdk: (Int) -> Unit,
    configureCompileOptions: () -> Unit,
) {
    setCompileSdk(ANDROID_COMPILE_SDK)
    setMinSdk(ANDROID_MIN_SDK)
    configureCompileOptions()
}

private fun Project.configureKotlinAndroidProject() {
    configure<KotlinAndroidProjectExtension> {
        compilerOptions.apply {
            jvmTarget = KotlinJvmCompatibility
            freeCompilerArgs.add(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            )
        }
    }
    dependencies { add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get()) }
}

/**
 * Configure base Kotlin options for JVM (non-Android)
 */
internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JvmCompatibility
        targetCompatibility = JvmCompatibility
    }

    configure<KotlinJvmProjectExtension> {
        compilerOptions.apply {
            jvmTarget = KotlinJvmCompatibility
            freeCompilerArgs.add(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            )
        }
    }
}


