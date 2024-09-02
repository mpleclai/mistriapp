plugins {
    alias(libs.plugins.mistriapp.android.feature)
    alias(libs.plugins.mistriapp.android.library.compose)
}

android {
    namespace = "com.thedullpencil.home"
}

dependencies {
    implementation(project(":data"))
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.ext.compiler)
    implementation(libs.javax.inject)

    testImplementation(libs.androidx.compose.ui.test)
    testImplementation(libs.robolectric)
}