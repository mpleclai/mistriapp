plugins {
    alias(libs.plugins.mistriapp.android.library)
    alias(libs.plugins.kotlinx.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
