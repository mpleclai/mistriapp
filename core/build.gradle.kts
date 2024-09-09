plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.mistriapp.android.library)
}

android {
    namespace = "com.thedullpencil.core"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.android)
}