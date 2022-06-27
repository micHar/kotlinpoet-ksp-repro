Quick repro project for for https://github.com/square/kotlinpoet/issues/1273

1. `gradle build` - should compile just fine and generate `build/generated/ksp/metadata/commonMain/kotlin/com/example/repro/example.kt`
2. Uncomment `add("kspIosArm64", project(":processor"))` and comment `add("kspCommonMainMetadata", project(":processor"))` in `shared/build.gradle.kts`.
3. `gradle build` again - should throw `java.lang.NoClassDefFoundError: javax/lang/model/type/TypeMirror` and not compile.
