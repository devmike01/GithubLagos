package com.example.features.features.favorites

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.common.activities.BaseActivity
import com.example.features.R
import com.example.features.features.adapters.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteActivity : BaseActivity(){


    lateinit var userAdapter: UserAdapter

    private lateinit var progressBar : ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun bindViews() {

    }

    override fun getLayout(): Int {
        return R.layout.favorite_layout_activity
    }

    override fun populateViews() {
        userAdapter = UserAdapter()
    }

}