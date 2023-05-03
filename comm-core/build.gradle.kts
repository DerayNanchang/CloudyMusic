plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.lsn.comm.core"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        //路由框架配置
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }
    }

    buildTypes {
        val apiServiceUrlD = libs.versions.httpApiServerUrlDevelop.get()
        val appFileUrlD = libs.versions.httpApiServerFileDevelop.get()
        val apiServiceUrlR = libs.versions.httpApiServerUrlDevelop.get()
        val appFileUrlR = libs.versions.httpApiServerFileDevelop.get()
        val appName = libs.versions.appName.get()

        debug {
            buildConfigField("String", "apiServiceUrl", "\"$apiServiceUrlD\"")
            buildConfigField("String", "appFileUrl", "\"$appFileUrlD\"")
            buildConfigField("String", "appName", "\"$appName\"")
        }

        release {
            buildConfigField("String", "apiServiceUrl", "\"$apiServiceUrlR\"")
            buildConfigField("String", "appFileUrl", "\"$appFileUrlR\"")
            buildConfigField("String", "appName", "\"$appName\"")
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

    dataBinding {
        enable = true
    }
}

dependencies {


    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.mmkv)
    // Kotlin
    implementation(libs.jetbrains.kotlin.stdlib)
    // 协程核心库
    implementation(libs.jetbrains.kotlinx.coroutines.core)
    // 协程 Android 支持库
    implementation(libs.jetbrains.kotlinx.coroutines.android)
    // lifecycle 对于协程的扩展封装
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    api(project(":lib-base"))
    api(project(":lib-net"))

    api("com.github.Neo-Turak:renderscript-toolkit:v0.8")

    /**路由框架，组件必须配置，否则调用失败*/
    implementation(libs.alibaba.arouter.api)
    kapt(libs.alibaba.arouter.compiler)
}

kapt {
    correctErrorTypes = true
}