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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.activities.BaseActivity
import com.example.common.extensions.hide
import com.example.common.extensions.show
import com.example.common.extensions.showMessage
import com.example.common.states.Status
import com.example.features.R
import com.example.features.features.adapters.FavoriteUserAdapter
import com.example.features.features.adapters.UserAdapter
import com.example.features.features.details.DetailsFragment
import com.example.features.utils.interfaces.OnClickUserListener
import com.example.features.utils.interfaces.OnDeleteFavoriteUserListener
import com.example.features.utils.interfaces.collectRecent
import com.example.features.utils.interfaces.submit
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteActivity : BaseActivity(){

    @Inject
    lateinit var favoriteUserAdapter: FavoriteUserAdapter

    private lateinit var progressBar : ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var parentView: RelativeLayout
    private lateinit var deleteAllBtn : ExtendedFloatingActionButton

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
        deleteAllBtn = findViewById(R.id.delete_all_btn)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = favoriteUserAdapter
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

        favoriteUserAdapter.addOnClickUserListener(object : OnClickUserListener{
            override fun onClickUser(login: String) {
                addFragment(R.id.fav_parent, DetailsFragment.newInstance(login))
            }
        })

        favoriteUserAdapter.addOnDeleteFavoriteUserListener(object : OnDeleteFavoriteUserListener{
            override fun onDeleteFavoriteUser(itemId: Int, position: Int) {
                favoriteViewModel.deleteFavorite(itemId, position)
            }
        })

        deleteAllBtn.setOnClickListener {
            favoriteViewModel.deleteAllFavorites()
        }

        favoriteViewModel.deleteFavorites.collectRecent(lifecycleScope) {
            when(it.status){
                Status.Loading -> {}
                Status.Success -> {
                    parentView.showMessage(it.data?.msg)
                    it.data?.position?.run {
                        favoriteUserAdapter.notifyItemRemoved(this)
                        //favoriteUserAdapter.notifyItemChanged(this)
                    }
                }
                Status.Failed -> {
                    parentView.showMessage(it.message)
                }
            }
        }

        favoriteViewModel.deleteAllFavorites.collectRecent(lifecycleScope) {
            when(it.status){
                Status.Loading -> {}
                Status.Success -> {
                    parentView.showMessage(it.data)

                }
                Status.Failed -> {
                    parentView.showMessage(it.message)
                }
            }
        }


        favoriteViewModel.favorites.collectRecent(lifecycleScope) {
            when(it.status){
                Status.Loading -> {
                    showProgress()
                }
                Status.Success -> {
                    hideProgress()
                    it.data?.run {
                        Log.d("Status.Success1", "${this}")
                        favoriteUserAdapter.submit(this@FavoriteActivity, this)
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