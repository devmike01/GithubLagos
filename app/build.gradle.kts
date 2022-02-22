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
        resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
    }
}


dependencies {
    implementation(Dependencies.HILT)
    implementation(project(":features"))
    kapt(Compilers.HILT)
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.5.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
}