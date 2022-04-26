plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":model"))
    implementation(project(":shared"))
    implementation(project(":test-shared"))
    androidTestImplementation(project(":test-shared"))

    implementation(Libs.Androidx.Room.RUNTIME)
    implementation(Libs.Androidx.Room.KTX)
    implementation(Libs.Kotlinx.COROUTINES_ANDROID)
    implementation(Libs.Dagger.DAGGER)

    kapt(Libs.Androidx.Room.COMPILER)
    kapt(Libs.Dagger.COMPILER)

    androidTestImplementation(Libs.Kotlinx.COROUTINES_TEST)
    androidTestImplementation(Libs.Androidx.ARCH_TEST_CORE)
    androidTestImplementation(Libs.Androidx.TEST_CORE_KTX)
    androidTestImplementation(Libs.Test.JUNIT)
    androidTestImplementation(Libs.Test.JUNIT_EXT)
    androidTestImplementation(Libs.Test.JUNIT_EXT_KTX)
    androidTestImplementation(Libs.Test.TRUTH)

    androidTestImplementation(Libs.Androidx.TEST_RULES)
    androidTestImplementation(Libs.Androidx.TEST_RUNNER)

    coreLibraryDesugaring(Libs.Core.DESUGAR)


}