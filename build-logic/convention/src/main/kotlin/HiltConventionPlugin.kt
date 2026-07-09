import com.thedullpencil.mistriapp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.google.dagger.hilt.android")
            apply(plugin = "org.jetbrains.kotlin.kapt")

            pluginManager.withPlugin("com.google.devtools.ksp") {
                dependencies {
                    add("ksp", libs.findLibrary("hilt.compiler").get())
                }
            }

            dependencies {
                add("kapt", libs.findLibrary("hilt.compiler").get())
                add("implementation", libs.findLibrary("hilt.core").get())
            }

            pluginManager.withPlugin("com.android.base") {
                dependencies {
                    add("implementation", libs.findLibrary("hilt.android").get())
                }
            }
        }
    }
}