plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.lsn.lib.obs"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        buildTypes {
            val umId = libs.versions.umid.get()
            val messageSecret = libs.versions.messageSecret.get()
            debug {
                buildConfigField("String", "umId", "\"$umId\"")
                buildConfigField("String", "messageSecret", "\"$messageSecret\"")
            }
            release {
                buildConfigField("String", "umId", "\"$umId\"")
                buildConfigField("String", "messageSecret", "\"$messageSecret\"")
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // 友盟基础组件库（所有友盟业务SDK都依赖基础组件库）
    api(libs.umeng.umsdk.common)// (必选)
    api(libs.umeng.umsdk.asms)// 必选
    api(libs.umeng.umsdk.apm)// U-APM产品包依赖(必选)

    //友盟Push依赖
//    api("com.umeng.umsdk:push:6.5.8")
    // debugImplementation because LeakCanary should only run in debug builds.
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")
}