package com.example.features.main

import androidx.paging.PagingData
import com.example.common.schedulers.TestCommonSchedulers
import com.example.common.states.Status
import com.example.common.states.UiStates
import com.example.core.repository.GithubRepositoryImpl
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.models.user.Item
import com.example.core.repository.paging.GithubPagingSource
import com.example.features.FakeGithubApiService
import com.example.features.FakeRepositoryImpl
import com.example.features.features.main.DeveloperListViewModel
import com.example.features.utils.FavoriteUserDaoTest
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.lang.Exception
import kotlin.jvm.Throws


class DeveloperListViewModelTest {


    lateinit var repository: FakeRepositoryImpl

    lateinit var closable : AutoCloseable

    lateinit var developerListViewModel : DeveloperListViewModel

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)


        val apiService = FakeGithubApiService()
        val favoriteUserDaoTest = FavoriteUserDaoTest();

        repository = spy(FakeRepositoryImpl(apiService, GithubPagingSource(apiService), favoriteUserDaoTest))
        developerListViewModel = DeveloperListViewModel(repository, TestCommonSchedulers())

        //RxAndroidPlugins.setInitMainThreadSchedulerHandler { TestCommonSchedulers().io() }
       // RxJavaPlugins.setInitIoSchedulerHandler { TestCommonSchedulers().io()  }

    }

    @Test
    fun testFetchUsers(){

        developerListViewModel.fetchUsers()
        verify(repository, times(1)).executeGetUsers()

        val responses = arrayListOf<Item>()
        for (i in 0..2){
            responses.add(
                Item(
                itemId = i,
                login = "name $i",
                avatarUrl = "img.com/$i.jpg",
                score = i.toDouble()
            )
            )
        }

        developerListViewModel.fetchUsers()

        runBlocking {
            val firstResult = developerListViewModel.users.first()

            assertEquals(firstResult.status, Status.Success)
          //  assertEquals(firstResult.data, isNotNull())
        }
    }

    @Test
    fun testFavoriteUser(){

        developerListViewModel.favoriteUser(FavoriteUser(2, "gbenga",
            2,"http", 1.0))
        verify(repository, times(1))
            .executeFavoriteUser(FavoriteUser(2, "gbenga",
                2,"http", 1.0))
        runBlocking {
            val result = developerListViewModel.favorite.first()
            assertEquals(result.status, Status.Success)
        }
    }

    @Before
    @Throws(Exception::class)
    fun init(){
       closable.close()
    }
}