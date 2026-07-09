plugins {
    alias(libs.plugins.mistriapp.android.library)
    alias(libs.plugins.mistriapp.android.test)
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.android)
    implementation(project(":data"))
    implementation(project(":core"))
}