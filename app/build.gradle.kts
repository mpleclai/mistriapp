plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.mistriapp.android.application)
//    alias(libs.plugins.ksp)
//    alias(libs.plugins.hilt)
}

android {
    namespace = "com.thedullpencil.mistriapp"

    defaultConfig {
        applicationId = "com.thedullpencil.mistriapp"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":feature:villagers"))
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.material)
    implementation(libs.hilt.android)
//    kapt(libs.hilt.compiler)
//    implementation(libs.compose.)
//    implementation(libs.hilt.core)
//    implementation(libs.hilt.ext.compiler)
//    implementation(libs.hilt.ext.work)
//    implementation(libs.hilt.compiler)
//    implementation(libs.hilt.android)
    implementation(libs.androidx.work)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.androidx.adaptive.android)
    implementation(libs.androidx.material3.adaptive.navigation.suite.android)

    testImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(libs.androidx.ui.test.manifest)
}
