// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.3.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.0"
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
    id("com.android.library") version "8.3.2" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1" apply false
}