import org.gradle.api.artifacts.dsl.DependencyHandler

object Libs {
    private const val IMPLEMENTATION = "implementation"

    object Core {
        const val DESUGAR = "com.android.tools:desugar_jdk_libs:1.1.5"
    }

    object Test {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.JUNIT_EXT}"
        const val JUNIT_EXT_KTX = "androidx.test.ext:junit-ktx:${Versions.JUNIT_EXT_KTX}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
        const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}"
        const val TRUTH = "com.google.truth:truth:${Versions.TRUTH}"
    }

    object Androidx {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
        const val ANDROID_MATERIAL =
            "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"

        const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY}"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT}"
        const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${Versions.LEGACY_SUPPORT}"

        const val TEST_CORE_KTX = "androidx.test:core-ktx:${Versions.ANDROIDX_TEST}"
        const val ARCH_TEST_CORE = "androidx.arch.core:core-testing:${Versions.ARCH_TEST_CORE}"
        const val TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
        const val TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"

        object Lifecycle {
            const val RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
            const val LIVEDATA_KTX =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
            const val VIEW_MODEL_KTX =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
        }

        object Navigation {
            const val RUNTIME_KTX =
                "androidx.navigation:navigation-runtime-ktx:${Versions.NAVIGATION_KTX}"
            const val FRAGMENT_KTX =
                "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_KTX}"
            const val UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_KTX}"
        }

        object Room {
            const val RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
            const val COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
            const val KTX = "androidx.room:room-ktx:${Versions.ROOM}"
            const val TEST = "androidx.room:room-testing:${Versions.ROOM}"
            const val COMMON = "androidx.room:room-common:${Versions.ROOM}"
        }

        fun DependencyHandler.implementAppLibraries() {
            add(IMPLEMENTATION, CORE_KTX)
            add(IMPLEMENTATION, APPCOMPAT)
            add(IMPLEMENTATION, ANDROID_MATERIAL)
            add(IMPLEMENTATION, CONSTRAINT_LAYOUT)
        }
    }

    object Kotlinx {
        const val COROUTINES_ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
        const val COROUTINES_TEST =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_TEST}"
    }

    object Retrofit {
        const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
        const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT2}"
        const val GSON_CONVERTER =
            "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT2_GSON_CONVERTOR}"
    }

    object OkHttp {
        const val OKHTTP3 = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP3}"
        const val LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP3}"
    }

    object Dagger {
        const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
        const val COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
    }

    object Glide {
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
        const val COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
    }

}