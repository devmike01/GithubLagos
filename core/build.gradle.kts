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
    api(Dependencies.ROOM_PAGING)
    implementation(Dependencies.COMMON_PAGING)
    implementation("androidx.paging:paging-rxjava2-ktx:3.1.0")
    kapt(Compilers.ROOM)
    kapt(Compilers.HILT)
    implementation(Dependencies.GSON)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RXJAVA2_ROOM)
    implementation(Dependencies.GSON_CONVERTER)
    api(Dependencies.HILT)
    api(Dependencies.KTS_CORE)
    api(Dependencies.ADAPTER_RXJAVA)
    api(platform(Dependencies.OKHTTP_BOM))
    api(Dependencies.OKHTTP)
    api(Dependencies.OKHTTP_LOGGING)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
