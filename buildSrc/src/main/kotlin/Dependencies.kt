import org.gradle.api.JavaVersion

object Versions{
    const val COROUTINE_FLOW ="1.6.0"
    const val KTX_CORE ="1.7.0"
    const val HILT ="2.41"
    const val APP_COMPAT ="1.4.1"
    const val MATERIAL ="1.5.0"
    const val GSON ="2.9.0"
    const val RETROFIT ="2.9.0"
    const val NAVIGATION_FRAGMENT ="2.4.0"
    const val ROOM = "2.4.1"
    const val JUNIT ="4.13.2"
    const val EXT_JUNIT ="1.1.3"
    const val ESPRESSO_CORE = "3.4.0"

    //Kapt versions
    const val KAPT =  "1.6.10"

    // Default config versions
    const val MIN_SDK =21
    const val TARGET_SDK = 31
    const val COMPILE_SDK = 31

    //Compile options
    val SOURCE_COMPATIBILITY = JavaVersion.VERSION_1_8
    val TARGET_COMPATIBILITY = JavaVersion.VERSION_1_8
    val JVM_TARGET ="1.8"

    //RxJava
    const val RX_ANDROID ="2.1.1"
    const val RX_JAVA ="2.2.21"
    const val RX_JAVA_CONVERTER ="2.9.0"

    //Paging
    const val PAGING = "2.1.2"
    const val ROOM_PAGING ="2.4.0-alpha04"
    const val PAGING_RUNTIME ="3.1.0"

    //Okhttp
    const val OKHTTP_BOM ="4.9.3"

    //Viewmodel
    const val VIEWMODEL_KTX ="2.5.0-alpha02"

    //Glide
    const val GLIDE = "4.13.0"
}

object Dependencies {
    const val PAGING_RXJAVA = "androidx.paging:paging-rxjava2-ktx:${Versions.PAGING_RUNTIME}"
    const val RXJAVA2_ADAPTER ="com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.RX_JAVA_CONVERTER}"
    const val PAGING_RXJAVA2 ="androidx.paging:paging-runtime:${Versions.PAGING}"
    const val PAGING ="androidx.paging:paging-runtime:${Versions.PAGING}"
    const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.VIEWMODEL_KTX}"
    const val VIEWMODEL_KTX ="androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.VIEWMODEL_KTX}"
    const val RX_ANDROID3 = "io.reactivex.rxjava2:rxandroid:${Versions.RX_ANDROID}"
    const val RX_JAVA3 ="io.reactivex.rxjava2:rxjava:${Versions.RX_JAVA}"
    const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val KTS_CORE = "androidx.core:core-ktx:${Versions.KTX_CORE}"
    const val APPCOMPAT ="androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val MATERIAL ="com.google.android.material:material:${Versions.MATERIAL}"
    const val NAVIGATION_FRAGMENT ="androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_FRAGMENT}"
    const val GSON ="com.google.code.gson:gson:${Versions.GSON}"
    const val RETROFIT ="com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val ROOM ="androidx.room:room-runtime:${Versions.ROOM}"
    const val RXJAVA2_ROOM ="androidx.room:room-rxjava2:${Versions.ROOM}"
    const val GSON_CONVERTER ="com.squareup.retrofit2:converter-gson:${Versions.GSON}"
    const val ROOM_PAGING ="androidx.room:room-paging:${Versions.ROOM_PAGING}"
    const val COMMON_PAGING ="androidx.paging:paging-common-ktx:3.1.0"
    const val ADAPTER_RXJAVA ="com.squareup.retrofit2:adapter-rxjava2:${Versions.RX_JAVA_CONVERTER}"
    const val OKHTTP_BOM ="com.squareup.okhttp3:okhttp-bom:${Versions.OKHTTP_BOM}"
    const val OKHTTP ="com.squareup.okhttp3:okhttp"
    const val OKHTTP_LOGGING ="com.squareup.okhttp3:logging-interceptor"
    const val FRAGMENT_KTX ="androidx.fragment:fragment-ktx:1.4.1"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val PAGING_RUNTIME ="androidx.paging:paging-runtime-ktx:${Versions.PAGING_RUNTIME}"
}

object LocalDependencies {
    const val CORE = ":core"
    const val COMMON = ":common"
}

object Compilers {
    const val HILT = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    const val ROOM ="androidx.room:room-compiler:${Versions.ROOM}"
    const val GLIDE ="com.github.bumptech.glide:compiler:${Versions.GLIDE}"
}

object TestDependencies {
    const val ESPRESSO_CORE ="androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    const val EXT_JUNIT ="androidx.test.ext:junit:${Versions.EXT_JUNIT}"
    const val JUNIT ="junit:junit:${Versions.JUNIT}"
    const val ANDROID_JUNIT_RUNNER ="androidx.test.runner.AndroidJUnitRunner"
}

/*

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
 */
