import Libs.Androidx.implementAppLibraries

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.example.todo"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.VERSION_CODE
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":model"))
    implementation(project(":data"))
    implementation(project(":database"))


    implementAppLibraries()

    implementation(Libs.Androidx.ACTIVITY_KTX)
    implementation(Libs.Androidx.FRAGMENT_KTX)
    implementation(Libs.Androidx.LEGACY_SUPPORT)

    implementation(Libs.Androidx.Lifecycle.RUNTIME)
    implementation(Libs.Androidx.Lifecycle.LIVEDATA_KTX)
    implementation(Libs.Androidx.Lifecycle.VIEW_MODEL_KTX)

    implementation(Libs.Androidx.Navigation.FRAGMENT_KTX)
    implementation(Libs.Androidx.Navigation.RUNTIME_KTX)
    implementation(Libs.Androidx.Navigation.UI_KTX)

    implementation(Libs.Test.TIMBER)

    implementation(Libs.Dagger.DAGGER)
    implementation(Libs.Dagger.COMPILER)

    testImplementation(Libs.Test.JUNIT)
    androidTestImplementation(Libs.Test.JUNIT_EXT)
    androidTestImplementation(Libs.Test.ESPRESSO)
}