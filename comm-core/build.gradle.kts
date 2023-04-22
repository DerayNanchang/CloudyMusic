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

    implementation("com.tencent:mmkv:1.2.15")
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.30")
    // 协程核心库
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    // 协程 Android 支持库
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // lifecycle 对于协程的扩展封装
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")


    api(project(":lib-base"))
    api(project(":lib-net"))

    /**路由框架，组件必须配置，否则调用失败*/
    implementation("com.alibaba:arouter-api:1.5.1")
    kapt("com.alibaba:arouter-compiler:1.5.1")
}

kapt {
    correctErrorTypes = true
}