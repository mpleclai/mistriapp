import com.thedullpencil.mistriapp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Convention plugin that centralizes common Compose UI test dependencies.
 *
 * Automatically adds Compose UI unit-test dependencies (ui-test-junit4) and Robolectric
 * that are repeated across Compose modules.
 *
 * Usage in build.gradle.kts:
 * ```kotlin
 * plugins {
 *     alias(libs.plugins.mistriapp.compose.testing)
 * }
 * ```
 */
class ComposeTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("testImplementation", libs.findLibrary("androidx.compose.ui.test").get())
                add("testImplementation", libs.findLibrary("robolectric").get())
            }
        }
    }
}

