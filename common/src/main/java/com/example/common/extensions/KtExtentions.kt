package com.example.common.extensions

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Single

fun FragmentActivity.show(progressBar: ProgressBar?){
    progressBar?.visibility = View.VISIBLE
}

fun FragmentActivity.hide(progressBar: ProgressBar?){
    progressBar?.visibility = View.GONE
}

fun View.showMessage(msg: String){
    Snackbar.make(this, msg, Snackbar.LENGTH_LONG).show()
}
