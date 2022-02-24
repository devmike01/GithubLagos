package com.example.features.features.main

import android.util.Log
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.activities.BaseActivity
import com.example.common.extensions.hide
import com.example.common.extensions.show
import com.example.common.states.Status
import com.example.features.R
import com.example.features.features.adapters.UserAdapter
import com.example.features.features.details.DetailsFragment
import com.example.features.utils.interfaces.OnClickUserListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
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
        progressBar = findViewById(R.id.progressar)
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
        userAdapter.addOnClickUserListener(object : OnClickUserListener{
            override fun onClickUser(login: String) {
                addFragment(R.id.details_fragment, DetailsFragment.newInstance(login), isAddBackStack = true)
            }

        })
        recyclerView?.adapter = userAdapter
        viewModel.fetchUsers(1)
        lifecycleScope.launchWhenCreated {
            viewModel.users.collectLatest {
                when(it.status){
                    Status.Loading -> {
                        showProgress()
                    }
                    Status.Success -> {
                        hideProgress()
                        it.data?.run {
                            Log.d("Status.Success", "$this}")
                            userAdapter.submitData(this)
                        }
                    }
                    Status.Failed -> {
                        hideProgress()

                    }
                }
            }
        }
    }
}