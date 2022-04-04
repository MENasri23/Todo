import org.gradle.api.artifacts.dsl.DependencyHandler

object Libs {

    object Test {
        const val JUNIT = "junit:junit:4.13:${Versions.JUNIT}"
        const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.JUNIT_EXT}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    }

    object Androidx {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
        const val ANDROID_MATERIAL =
            "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"

        object Lifecycle {
            const val LIFECYCLE_LIVEDATA_KTX =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_LIVEDATA_KTX}"
            const val LIFECYCLE_VIEW_MODEL_KTX =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VIEW_MODEL_KTX}"
        }

        object Navigation {
            const val LIVEDATA_KTX =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_LIVEDATA_KTX}"
            const val UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_KTX}"
        }

        object Room {
            const val RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
            const val COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
            const val ktx = "androidx.room:room-ktx:${Versions.ROOM}"
        }

        fun DependencyHandler.implementAppLibraries() {
            add("implementation", CORE_KTX)
            add("implementation", APPCOMPAT)
            add("implementation", ANDROID_MATERIAL)
            add("implementation", CONSTRAINT_LAYOUT)
        }
    }

    object Kotlinx {
        const val COROUTINES =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE}"
    }

    object Retrofit {
        const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
        const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT2}"
        const val GSON_CONVERTER =
            "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT2_GSON_CONVERTOR}"
    }

    object OkHttp {
        const val LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP3_LOGGING_INTERCEPTOR}"
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