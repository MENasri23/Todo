import Libs.Androidx.implementAppLibraries

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":model"))
    implementation(project(":database"))
    testImplementation(project(":test-shared"))

    implementAppLibraries()

    implementation(Libs.Kotlinx.COROUTINES_ANDROID)

    testImplementation(Libs.Test.JUNIT)
    testImplementation(Libs.Test.JUNIT_EXT)
    testImplementation(Libs.Kotlinx.COROUTINES_TEST)
}