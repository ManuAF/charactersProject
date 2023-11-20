package com.devexperto.characterscoders.buildsrc

object Libs{
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.4.0"
    const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.42.0"
    object Kotlin{
        private const val version = "1.9.0"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object AndroidX {
        object Navigation {
            private const val version = "2.7.4"
            const val gradlePlugin = "androidx.navigation.safeargs:androidx.navigation.safeargs.gradle.plugin:$version"
        }
    }



}