plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.mistriapp.android.library)
}

android {
    namespace = "com.thedullpencil.core"
}


tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.android)

    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinTest)
    testImplementation(libs.junit.jupiter)
}