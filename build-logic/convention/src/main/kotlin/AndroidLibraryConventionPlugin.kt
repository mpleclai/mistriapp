import com.android.build.api.dsl.LibraryExtension
import com.thedullpencil.mistriapp.androidNamespace
import com.thedullpencil.mistriapp.calculateResourcePrefix
import com.thedullpencil.mistriapp.configureKotlinAndroid
import com.thedullpencil.mistriapp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.android")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                namespace = androidNamespace()
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                testOptions.animationsDisabled = true
                testOptions.unitTests.isIncludeAndroidResources = true
                resourcePrefix = calculateResourcePrefix(path)
            }
            dependencies {
                add("implementation", libs.findLibrary("androidx.tracing.ktx").get())
            }
        }
    }
}