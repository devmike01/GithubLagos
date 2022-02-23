package com.example.features

import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayout())
        bindViews()
        populateViews()
    }

   abstract fun bindViews()

    @LayoutRes abstract  fun getLayout(): Int

    abstract fun populateViews()

    fun addFragment(@IdRes container: Int, fragment: Fragment, isAddBackStack: Boolean = false){
        supportFragmentManager.beginTransaction()
            .add(container, fragment)
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out).run {
                    if(isAddBackStack){
                        addToBackStack(fragment.tag)
                    }
                commit()
            }
    }

    fun replaceFragment(@IdRes container: Int, fragment: Fragment, isAddBackStack: Boolean = false){
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out).run {
                if(isAddBackStack){
                    addToBackStack(fragment.tag)
                }
                commit()
            }
    }
}