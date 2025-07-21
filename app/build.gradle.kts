plugins {
    id("com.google.devtools.ksp") version "2.1.21-2.0.1"
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20"
    alias(libs.plugins.jetbrains.kotlin.serialization)

}

android {
    namespace = "com.romakost.test_compose"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.romakost.test_compose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes.forEach {
        val API_KEY = "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NmRmMTJkMWQ2NjUzNTNkZTYxNGU4MzZhNjQ4NWQ3NyIsInN1YiI6IjY1ZWNmZWY2OGNmY2M3MDE2NDYyNDcyMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xvKtfWuH8buXRnKitrGijUbMHw6D8cNqEtsiHCzczr4\""
        it.buildConfigField("String", "API_KEY", API_KEY)
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

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core"))

    implementation(project(":home"))
    implementation(project(":favorites"))
    implementation(project(":trend_movie"))
    implementation(project(":profile"))

    //dagger-hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation (libs.lifecycle.extensions)
    implementation(libs.core.splashscreen)

    // navigation
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.material3.adaptive.navigation3)
    implementation(libs.kotlinx.serialization.core)

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
    implementation(libs.junit.ktx)
    implementation(libs.kotlinx.collections.immutable)

}
