plugins {
    `android-library`
    `kotlin-android`
    id("org.jetbrains.kotlin.plugin.compose")
}

// use "-PenableComposeCompilerReports=true" to enable
if ((project.findProperty("enableComposeCompilerReports") as? String).toBoolean()) {
    composeCompiler {
        reportsDestination = project.layout.buildDirectory.dir("compose-reports")
        metricsDestination = project.layout.buildDirectory.dir("compose-reports")
    }
}