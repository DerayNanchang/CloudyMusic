plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = libs.versions.appId.get()
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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
        dataBinding = true
    }


}

dependencies {

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(project(":comm-core"))
//    implementation(project(":comm-version"))
//    implementation(project(":comm-mqtt"))
}

/*
configurations.all {
    resolutionStrategy.eachDependency {
        val requested = requested
        if (requested.group == "androidx.annotation:annotation"){
            if (!requested.name.startsWith("annotation")) {
                useVersion("1.5.0")
            }
        }
    }
   resolutionStrategy {
        val requested = requested
        force("androidx.annotation:annotation:1.5.0")
        force("androidx.lifecycle:lifecycle-livedata:2.4.1")
        force("androidx.lifecycle:lifecycle:process:2.4.1")
        force("androidx.startup:startup-runtime:1.1.1")
        force("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
        force("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
    }
}*/

/*configurations.all {
    resolutionStrategy.eachDependency{
        val requested = requested
        if (requested.group == "com")
    }
}*/

kapt {
    correctErrorTypes = true
}