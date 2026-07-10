plugins {
    alias(libs.plugins.mistriapp.android.library)
    alias(libs.plugins.mistriapp.android.library.compose)
    alias(libs.plugins.mistriapp.compose.testing)
}


dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewModelCompose)
}