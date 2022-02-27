plugins {
    id (Plugins.ANDROID_APP)
    id (Plugins.JETBRAIN_KOTLIN)
    kotlin(Plugins.KAPT) version Versions.KAPT
    id(Plugins.HILT)
}

android {
    compileSdk =31

    defaultConfig {
        applicationId ="com.example.githublagosdevs"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = ("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
