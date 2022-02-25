package com.example.common.extensions

import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
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

fun View.showMessage(msg: String?){
    msg?.run {
        Snackbar.make(this@showMessage, this, Snackbar.LENGTH_LONG).show()
    }
}

/*
try {
            val users: List<Item> = service.getUsers(page = position)
                .subscribeOn(Schedulers.io()).blockingGet().items ?: emptyList()
            val nextKey = if (users.isEmpty()) {
                null
            } else {
                position + 1
            }

            LoadResult.Page(
                data = users,
                prevKey = if (position == GITHUB_PAGE_INDEX) null else position - 1,
                nextKey
            )
        }catch (exception: IOException){
            if(exception is UnknownHostException){
                LoadResult.Error(Exception("Please check your internet and try again!"))
            }else{
                Log.d("onBindViewHolder" , "$exception")
                LoadResult.Error(exception)
            }
        }catch (httpException: HttpException){
            LoadResult.Error(httpException)
        })
 */

@Suppress("unchecked_cast")
fun <T> Any.toSingle(): Single<T>{
    return Single.just(this as T)
}

fun String?.noNull(): String{
    val fallback = "n/a"
    return if(TextUtils.isEmpty(this)){
        fallback
    }else{
        this!!
    }
}

