import com.android.build.api.dsl.ApplicationExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.mistriapp.android.application)
    alias(libs.plugins.mistriapp.android.application.compose)
    alias(libs.plugins.mistriapp.hilt)
}

extensions.configure<ApplicationExtension> {
    defaultConfig {
        applicationId = "com.thedullpencil.mistriapp"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:villagers"))
    implementation(project(":feature:home"))
    implementation(project(":feature:museum"))
    implementation(project(":feature:fishing"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.navigationSuite)
    implementation(libs.hilt.android)

    testImplementation(libs.androidx.compose.ui.test)
    testImplementation(libs.robolectric)
}
