plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp") version "1.7.0-1.0.6"
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":annotation"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

dependencies {
//    fails with
//    e: java.lang.NoClassDefFoundError: javax/lang/model/type/TypeMirror
//    at com.squareup.kotlinpoet.CodeBlock$Builder.argToType(CodeBlock.kt:377)
//    at com.squareup.kotlinpoet.CodeBlock$Builder.addArgument(CodeBlock.kt:346)
//    at com.squareup.kotlinpoet.CodeBlock$Builder.add(CodeBlock.kt:318)
//    at com.example.processor.KspProcessor.process(LeProcessorProvider.kt:36)
//    add("kspIosArm64", project(":processor"))

//    works fine
    add("kspCommonMainMetadata", project(":processor"))
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
}