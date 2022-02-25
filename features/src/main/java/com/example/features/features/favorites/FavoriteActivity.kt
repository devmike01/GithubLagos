package com.example.features.features.favorites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.common.activities.BaseActivity
import com.example.common.extensions.hide
import com.example.common.extensions.show
import com.example.common.extensions.showMessage
import com.example.common.states.Status
import com.example.features.R
import com.example.features.features.adapters.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteActivity : BaseActivity(){

    @Inject
    lateinit var userAdapter: UserAdapter

    private lateinit var progressBar : ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var parentView: RelativeLayout

    private val favoriteViewModel : FavoriteActivityViewModel by viewModels()

    companion object{
        fun start(context: Context) =
            with(Intent(context, FavoriteActivity::class.java)){
            context.startActivity(this)
        }
    }

    override fun bindViews() {
        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress)
        parentView = findViewById(R.id.fav_parent)
    }

    override fun getLayout(): Int {
        return R.layout.favorite_layout_activity
    }
    private fun hideProgress(){
        hide(progressBar)
    }

    private fun showProgress(){
        show(progressBar)
    }


    override fun populateViews() {
        favoriteViewModel.fetchFavorites()

        lifecycleScope.launchWhenCreated {
            favoriteViewModel.favorites.collectLatest {
                when(it.status){
                    Status.Loading -> {
                        showProgress()
                    }
                    Status.Success -> {
                        hideProgress()
                        it.data?.run {
                            Log.d("Status.Success1", "$this}")
                          //  userAdapter.submitData(this)
                        }
                    }
                    Status.Failed -> {
                        hideProgress()
                        parentView.showMessage(it.message)
                    }
                }
            }
        }
    }

}