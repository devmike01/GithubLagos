plugins {
    id (Plugins.ANDROID_LIBRARY)
    id (Plugins.JETBRAIN_KOTLIN)
    kotlin(Plugins.KAPT) version Versions.KAPT
    id(Plugins.HILT)
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        testInstrumentationRunner = TestDependencies.ANDROID_JUNIT_RUNNER
        //consumerProguardFiles("consumer-rules.pro")
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
    packagingOptions{
        resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


dependencies {
    implementation(Dependencies.ROOM)
    api(Dependencies.PAGING)
    api(Dependencies.COMMON_PAGING)
    kapt(Compilers.ROOM)
    kapt(Compilers.HILT)
    implementation(Dependencies.GSON)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RXJAVA2_ROOM)
    implementation(Dependencies.GSON_CONVERTER)
    api(Dependencies.HILT)
    api(Dependencies.KTS_CORE)
    implementation(Dependencies.ADAPTER_RXJAVA)
    implementation(platform(Dependencies.OKHTTP_BOM))
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
