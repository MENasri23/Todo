plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(project(":shared"))
    // const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
}