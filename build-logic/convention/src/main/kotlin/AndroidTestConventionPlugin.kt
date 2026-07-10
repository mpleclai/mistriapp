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
 *     alias(libs.plugins.mistriapp.android.test)
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
            }

            listOf("com.android.library", "com.android.application").forEach { pluginId ->
                pluginManager.withPlugin(pluginId) {
                    addAndroidTestDependencies()
                }
            }
        }
    }

    private fun Project.addAndroidTestDependencies() {
        dependencies {
            add("testImplementation", libs.findLibrary("androidx.test.core").get())
            add("testImplementation", libs.findLibrary("androidx.test.ext").get())
            add("testImplementation", libs.findLibrary("robolectric").get())
        }
    }
}

