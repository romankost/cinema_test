plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
//    id ("kotlin-kapt")
    id("com.google.devtools.ksp") version "2.1.21-2.0.1"
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20"
}

android {
    namespace = "com.romakost.profile"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(project (":core"))

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // coroutine
    implementation(libs.kotlinx.coroutines.android)

    //dagger-hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation (libs.lifecycle.extensions)
    implementation(libs.hilt.navigation.compose)

    // compose
    implementation(libs.navigation.compose)
    implementation(platform(libs.androidx.compose.bom.v20241100))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)

}
