plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20"
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    namespace = "com.romakost.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes.forEach {
        val API_KEY = "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NmRmMTJkMWQ2NjUzNTNkZTYxNGU4MzZhNjQ4NWQ3NyIsIm5iZiI6MTcyOTc1ODYyNC4zMDg2ODEsInN1YiI6IjY1ZWNmZWY2OGNmY2M3MDE2NDYyNDcyMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.5FFv--aQzXNRMqIotVN2hQGKFAsIxdjpfEMf_YBQzFA\""
        it.buildConfigField("String", "API_KEY", API_KEY)

        val AI_KEY = "\"AIzaSyCZpeMR_QX6YUZfCrpgILbYNBtL0T-2g24\""
        it.buildConfigField("String", "AI_KEY", AI_KEY)

        val MODEL_NAME = "\"gemini-2.0-flash\""
        it.buildConfigField("String", "MODEL_NAME", MODEL_NAME)
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
        buildConfig = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    // coroutine
    implementation(libs.kotlinx.coroutines.android)

    // dagger-hilt
    implementation(libs.hilt.android)
    implementation(libs.ui.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.compose.bom.v20241100)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // coil
    implementation(libs.coil.compose)

    // icons
    implementation(libs.material.icons.core.android)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    // test
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
