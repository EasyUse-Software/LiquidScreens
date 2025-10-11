plugins {
    `android-application-config`
    `kotlin-parcelize`
}

android {
    namespace = "${project.group}.reimagined.sample"
}

kotlin {
    compilerOptions {
        optIn.addAll(
            "androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi",
            "dev.olshevski.navigation.reimagined.ExperimentalReimaginedApi"
        )
    }
}

dependencies {
    implementation(projects.reimaginedMaterial3)
    implementation(libs.compose.material.icons.extended)
    androidTestImplementation(projects.testUtils)
}