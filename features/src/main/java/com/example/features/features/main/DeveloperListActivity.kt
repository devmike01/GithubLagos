package com.example.features.features.main

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.extensions.hide
import com.example.common.extensions.show
import com.example.common.states.Status
import com.example.features.BaseActivity
import com.example.features.R
import com.example.features.features.adapters.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DeveloperListActivity : BaseActivity(){


    @Inject
    lateinit var userAdapter: UserAdapter

    private var progressBar : ProgressBar? = null
    private var recyclerView: RecyclerView? =null

    private val viewModel: DeveloperListViewModel by viewModels()


    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun bindViews(){
        progressBar = this.findViewById(R.id.progressar)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
    }

    private fun hideProgress(){
        hide(progressBar)
    }

    private fun showProgress(){
        show(progressBar)
    }

    override fun populateViews(){
       // userAdapter = UserAdapter()
        viewModel.fetchUsers(1)
        viewModel.users.observe(this){
            when(it.status){
                Status.Loading -> {
                    showProgress()
                }
                Status.Success -> {
                    hideProgress()
                  //  it.data.items?.run {         userAdapter.submitList(Pag) }

                }
                Status.Failed -> {
                    hideProgress()
                }
            }
        }
    }
}