plugins {
    id (Plugins.ANDROID_APP)
    id (Plugins.JETBRAIN_KOTLIN)
    kotlin(Plugins.KAPT) version Versions.KAPT
    id(Plugins.HILT)
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId ="com.example.githublagosdevs"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        testInstrumentationRunner = (TestDependencies.ANDROID_JUNIT_RUNNER)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Versions.JVM_TARGET
    }

    packagingOptions{
       // resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
    }
}


dependencies {
    implementation(Dependencies.HILT)
    implementation(project(":features"))
    implementation(project(LocalDependencies.COMMON))
    implementation(Dependencies.KTS_CORE)
    kapt(Compilers.HILT)
    implementation (Dependencies.APPCOMPAT)
    implementation (Dependencies.MATERIAL)
}

kapt {
    correctErrorTypes = true
}
