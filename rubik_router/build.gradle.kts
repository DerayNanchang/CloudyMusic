plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.lsn.module.provider"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        targetSdk = libs.versions.targetSdk.get().toInt()
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
//    kotlinOptions.freeCompilerArgs += ['-module-name', "kmd.mars.rubik.router"]
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":comm-core"))

/*    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation "org.jetbrains.kotlin:kotlin-reflect:$kotlin_version"
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.core:core-ktx:1.0.2'
    implementation 'com.google.code.gson:gson:2.8.5'*/

//    implementation deps.ktnail.x

    //    api project(':rubik_annotations')
//    api deps.rubik.annotations
}

/*
publishing {
    publications {
        rubikRouter(MavenPublication) {
            groupId 'com.rubik'
            artifactId 'router'
            version pubs.rubik.router
            description 'rubik'
            artifact("build/intermediates/aar_main_jar/rubikRelease/classes.jar")
            pom.withXml {
                def dependenciesNode = asNode().appendNode('dependencies')
                configurations.api.allDependencies.each {
                    if (it.group != null && (it.name != null || "unspecified" == it.name) && it.version != null) {
                        def dependencyNode = dependenciesNode.appendNode('dependency')
                        dependencyNode.appendNode('groupId', it.group)
                        dependencyNode.appendNode('artifactId', it.name)
                        dependencyNode.appendNode('version', it.version)
                    }
                }
                configurations.implementation.allDependencies.each {
                    if (it.group != null && (it.name != null || "unspecified" == it.name) && it.version != null) {
                        def dependencyNode = dependenciesNode.appendNode('dependency')
                        dependencyNode.appendNode('groupId', it.group)
                        dependencyNode.appendNode('artifactId', it.name)
                        dependencyNode.appendNode('version', it.version)
                    }
                }
            }
        }
    }

    apply from: '../rubik_maven_repositories.gradle'
    addRubikMavenRepos(repositories)
}*/
