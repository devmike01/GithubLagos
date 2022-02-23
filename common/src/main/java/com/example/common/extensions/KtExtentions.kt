package com.example.common.extensions

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.show(progressBar: ProgressBar?){
    progressBar?.visibility = View.VISIBLE
}

fun FragmentActivity.hide(progressBar: ProgressBar?){
    progressBar?.visibility = View.GONE
}