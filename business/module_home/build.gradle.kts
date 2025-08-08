plugins {
    if (libs.versions.isDebug.get().toBoolean()) {
        alias(libs.plugins.android.application)
    } else {
        alias(libs.plugins.android.library)
    }
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}


android {
    namespace = "com.example.home_module"
    compileSdk = rootProject.ext["compileSdk"] as Int
    defaultConfig {
        if (libs.versions.isDebug.get().toBoolean()) {
            defaultConfig.applicationId = "com.example.home"
            defaultConfig.versionCode = project.properties["versionCode"] as Int
            defaultConfig.versionName = project.properties["versionName"].toString()
        }
        minSdk = rootProject.ext["minSdk"] as Int
        targetSdk = rootProject.ext["targetSdk"] as Int
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName("main") {
            if (libs.versions.isDebug.get().toBoolean()) {
                manifest.srcFile("src/main/debug/AndroidManifest.xml")
                java.srcDir("src/main/debug/java")
            } else {
                manifest.srcFile("src/main/AndroidManifest.xml")
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    dependencies {
        implementation(project(":lib:lib_base"))
        implementation(project(":lib:lib_db"))
        kapt (libs.thearouter.apt)
        implementation(libs.hilt.android)
        kapt(libs.hilt.compiler)
    }
}