plugins {
    `android-library-config`
    `kotlin-parcelize`
}

android {
    namespace = "${project.group}.reimagined.material3"
}

dependencies {
    api(projects.reimagined)
    api(libs.compose.foundation)
    api(libs.compose.material3)
}
