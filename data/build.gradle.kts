plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.mistriapp.android.library)
    alias(libs.plugins.mistriapp.hilt)
}

android {
    namespace = "com.thedullpencil.data"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.serialization.json)
}