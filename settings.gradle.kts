pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Kotlinpoet_Ksp_TypeMirror_Repro"
include(":androidApp")
include(":shared")
include(":processor")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
include(":annotation")
