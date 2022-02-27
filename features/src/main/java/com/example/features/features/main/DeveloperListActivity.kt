package com.example.features.features.main

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.flatMap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.activities.BaseActivity
import com.example.common.extensions.hide
import com.example.common.extensions.show
import com.example.common.extensions.showMessage
import com.example.common.states.Status
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.features.R
import com.example.features.features.adapters.UserAdapter
import com.example.features.features.details.DetailsFragment
import com.example.features.features.favorites.FavoriteActivity
import com.example.features.utils.interfaces.OnClickUserListener
import com.example.features.utils.interfaces.OnFavoriteClickListener
import com.example.features.utils.interfaces.collectRecent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DeveloperListActivity : BaseActivity(){

    @Inject
    lateinit var userAdapter: UserAdapter

    private var progressBar : ProgressBar? = null
    private var recyclerView: RecyclerView? =null
    private var parentView : RelativeLayout? = null

    private val viewModel: DeveloperListViewModel by viewModels()


    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun bindViews(){
        progressBar = findViewById(R.id.progressar)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        parentView = findViewById(R.id.parent)
    }

    private fun hideProgress(){
        hide(progressBar)
    }

    private fun showProgress(){
        show(progressBar)
    }

    override fun populateViews(){

        userAdapter.addOnClickUserListener(object : OnClickUserListener{
            override fun onClickUser(login: String) {
                addFragment(R.id.details_fragment, DetailsFragment.newInstance(login), isAddBackStack = true)
            }
        })

        userAdapter.addOnFavoriteClickListener(object : OnFavoriteClickListener{
            override fun onFavoriteClick(favoriteUser: FavoriteUser) {
                viewModel.favoriteUser(favoriteUser)
            }
        })

        recyclerView?.adapter = userAdapter
        viewModel.fetchUsers()


        lifecycleScope.launch {
            val currentStateFlow = userAdapter.loadStateFlow
            currentStateFlow.collectLatest{ currentState ->
                Log.d("currentState", "currentState $currentState")
                when(val state = currentState.refresh){
                    is LoadState.Error -> {

                        Log.d("currentState", "state $state")
                        parentView?.showMessage(state.error.message)
                    }
                    else -> {}
                }
            }
        }


        viewModel.users.collectRecent(lifecycleScope) {
            when(it.status){
                Status.Loading -> {
                    showProgress()
                }
                Status.Success -> {
                    hideProgress()
                   lifecycleScope.launch {
                       it.data?.run {
                           Log.d("Status_.Success", "${this.flatMap { d ->
                               Log.d("Status_Status", "Status => $d")
                               listOf("${d.avatarUrl}") }}}")
                           userAdapter.submitData(this)
                       }
                   }
                }
                Status.Failed -> {
                    hideProgress()
                    parentView?.showMessage(it.message)
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.favorite.collectLatest {
                when(it.status){
                    Status.Loading -> {
                        showProgress()
                    }
                    Status.Success -> {
                        hideProgress()
                        parentView?.showMessage(it.data)
                    }
                    Status.Failed -> {
                        hideProgress()
                        parentView?.showMessage(it.message)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.dev_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(item.itemId == R.id.favorite_btn){
            FavoriteActivity.start(this)
            true
        }else{
            super.onOptionsItemSelected(item)
        }
    }

}