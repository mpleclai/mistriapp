plugins {
    alias(libs.plugins.mistriapp.android.library)
    alias(libs.plugins.mistriapp.android.test)
}


tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.android)

    testImplementation(libs.junit.jupiter)
}