import com.android.build.api.dsl.ApplicationExtension
import com.thedullpencil.mistriapp.androidNamespace
import com.thedullpencil.mistriapp.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

private const val TARGET_SDK = 34

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.android")

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                namespace = androidNamespace()
                defaultConfig.targetSdk = TARGET_SDK
                testOptions.animationsDisabled = true
            }
        }
    }
}