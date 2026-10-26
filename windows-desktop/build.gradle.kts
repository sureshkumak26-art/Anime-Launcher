plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version "1.8.2"
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "com.animecloud.animelauncher"
version = "0.1.0"

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
}

kotlin {
    jvmToolchain(17)
}

compose.desktop {
    application {
        mainClass = "com.animecloud.animelauncher.MainKt"
        nativeDistributions {
            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Exe)
            packageName = "Anime Launcher"
            packageVersion = "0.1.0"
            description = "Anime Launcher for Windows"
            vendor = "Anime Launcher"
            windows {
                console = false
                dirChooser = true
                perUserInstall = true
                menuGroup = "Anime Launcher"
            }
        }
    }
}
