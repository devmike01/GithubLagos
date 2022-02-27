plugins {
    id (Plugins.ANDROID_LIBRARY)
    id (Plugins.JETBRAIN_KOTLIN)
    kotlin(Plugins.KAPT) version Versions.KAPT
    id(Plugins.HILT)
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.HILT)
    kapt(Compilers.HILT)
    implementation(Dependencies.RX_JAVA3)
    kapt(Compilers.GLIDE)
    implementation(Dependencies.GLIDE)
    implementation(Dependencies.LIVEDATA_KTX)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    api(project(LocalDependencies.CORE))
    api(project(LocalDependencies.COMMON))
    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.MOCKITO_KOTLIN)
    androidTestImplementation(TestDependencies.EXT_JUNIT)
    androidTestImplementation(TestDependencies.ESPRESSO_CORE)
    api(Dependencies.PAGING)
    api(Dependencies.COMMON_PAGING)
    api(Dependencies.PAGING_RXJAVA2)
    api(Dependencies.PAGING_RUNTIME)
}