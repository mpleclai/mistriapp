plugins {
    alias(libs.plugins.mistriapp.android.library)
    alias(libs.plugins.mistriapp.android.test)
    alias(libs.plugins.mistriapp.hilt)
    alias(libs.plugins.kotlinx.serialization)
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.serialization.json)
}