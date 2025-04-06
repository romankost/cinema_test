plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "2.1.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20"
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    namespace = "com.romakost.trend_movie"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
        compose = true
        buildConfig = true
    }

    ktlint {
        version.set("0.48.0")
        android.set(true)
        enableExperimentalRules.set(false)
    }
}

dependencies {
    // modules
    implementation(project(":core"))
    implementation(project(":favorites"))

    implementation(libs.jetbrains.kotlinx.serialization.json)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // coroutine
    implementation(libs.kotlinx.coroutines.android)

    // dagger-hilt
    implementation(libs.hilt.android)
    debugImplementation(libs.ui.tooling)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    implementation(libs.lifecycle.extensions)
    implementation(libs.hilt.navigation.compose)

    // compose
    implementation(libs.navigation.compose)
    implementation(platform(libs.compose.bom))

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
}
