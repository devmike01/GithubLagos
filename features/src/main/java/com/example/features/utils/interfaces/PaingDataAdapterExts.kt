package com.example.features.utils.interfaces

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.features.features.adapters.FavoriteUserAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


fun <T: Any> PagingDataAdapter<T,
        FavoriteUserAdapter.UserViewHolder>.submit(activity: AppCompatActivity, pagingData: PagingData<T>){
    activity.lifecycleScope.launchWhenCreated {
        submitData(pagingData)
    }
}

fun <T> Flow<T>.collectRecent(lifecycleScope: LifecycleCoroutineScope, call : (T) -> Unit){
    lifecycleScope.launch {
        collectLatest {
            call.invoke(it)
        }
    }
}

