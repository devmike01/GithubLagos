plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin(Plugins.KAPT) version Versions.KAPT
    id(Plugins.HILT)
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        testInstrumentationRunner = TestDependencies.ANDROID_JUNIT_RUNNER
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
        sourceCompatibility = Versions.SOURCE_COMPATIBILITY
        targetCompatibility = Versions.TARGET_COMPATIBILITY
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.RX_ANDROID3)
    implementation(Dependencies.RX_JAVA3)
    implementation(Dependencies.VIEWMODEL_KTX)
    implementation(Dependencies.HILT)
    kapt(Compilers.HILT)
    kapt(Compilers.GLIDE)
    implementation(Dependencies.KTS_CORE)
    implementation(Dependencies.GLIDE)
    implementation(Dependencies.MATERIAL)

}