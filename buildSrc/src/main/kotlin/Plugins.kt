import org.gradle.kotlin.dsl.PluginDependenciesSpecScope
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec


object Plugins {

     const val HILT = "dagger.hilt.android.plugin"
     const val KAPT = "kapt"
     const val JETBRAIN_KOTLIN = "org.jetbrains.kotlin.android"
     const val ANDROID_APP ="com.android.application"
    const val ANDROID_LIBRARY ="com.android.library"

}