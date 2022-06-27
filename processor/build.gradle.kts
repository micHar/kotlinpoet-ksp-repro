plugins {
    kotlin("multiplatform")
    id("java-library")
}

kotlin {

    jvm {
        val main by compilations.getting {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {

        val jvmMain by getting {
            dependencies {

                val kotlinpoetVersion = "1.11.0"
                implementation("com.squareup:kotlinpoet:$kotlinpoetVersion")
                implementation("com.squareup:kotlinpoet-metadata:$kotlinpoetVersion")
                implementation("com.squareup:kotlinpoet-ksp:$kotlinpoetVersion")

                implementation("com.google.devtools.ksp:symbol-processing-api:1.7.0-1.0.6")

                implementation(project(":annotation"))

            }
        }
    }
}