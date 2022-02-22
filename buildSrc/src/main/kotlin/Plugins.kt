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

    fun configAndroidApp(plugins: PluginDependenciesSpec){
        plugins.run{
            id("com.android.application")
            id(JETBRAIN_KOTLIN)
            id(HILT)
        }
    }


    fun configLibrary(plugins: PluginDependenciesSpec){
        plugins.run{
            id("kotlin-kapt")
            id("org.jetbrains.kotlin.jvm")
            id("java-library")
            id(HILT)
        }
    }

}