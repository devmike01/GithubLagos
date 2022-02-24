package com.example.common.activities

import android.os.Bundle
import android.view.View
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

    //fun<V: View> bindView(@IdRes id: Int): V =  findViewById<V>(id)

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