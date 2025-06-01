plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20"
    id("org.jlleitschuh.gradle.ktlint")
    id("com.google.devtools.ksp") version "2.1.21-2.0.1"
}

android {
    namespace = "com.romakost.tv_show"
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
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

//    kapt {
//        correctErrorTypes = true
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core"))

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // coroutine
    implementation(libs.kotlinx.coroutines.android)

    // dagger-hilt
    implementation(libs.hilt.android)
    debugImplementation(libs.androidx.ui.tooling)
    ksp(libs.hilt.android.compiler)

    implementation(libs.lifecycle.extensions)
    implementation(libs.hilt.navigation.compose)

    // room
    ksp (libs.room.compiler)
    implementation(libs.androidx.room.runtime.v271)
    implementation(libs.androidx.room.ktx)

    // compose
    implementation(libs.navigation.compose)

    implementation(platform(libs.compose.bom.v20241100))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
