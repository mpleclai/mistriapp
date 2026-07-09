import com.thedullpencil.mistriapp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Convention plugin that centralizes common test dependencies for Android modules.
 *
 * Usage in build.gradle.kts:
 * ```kotlin
 * plugins {
 *     alias(libs.plugins.mistriapp.android.test.dependencies)
 * }
 * ```
 *
 * This automatically adds mockk, junit, and coroutines-test dependencies
 * with consistent versions managed in the version catalog.
 */
class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Core testing
                add("testImplementation", libs.findLibrary("mockk").get())
                add("testImplementation", libs.findLibrary("junit").get())
                add("testImplementation", libs.findLibrary("kotlinTest").get())

                // Coroutines testing
                add("testImplementation", libs.findLibrary("kotlinx.coroutines.test").get())

                // Optional: Android-specific test utilities (only if this is an Android module)
                if (project.plugins.hasPlugin("com.android.library") ||
                    project.plugins.hasPlugin("com.android.application")) {
                    add("testImplementation", libs.findLibrary("androidx.test.core").get())
                    add("testImplementation", libs.findLibrary("androidx.test.ext").get())
                    add("testImplementation", libs.findLibrary("robolectric").get())
                }
            }
        }
    }
}

