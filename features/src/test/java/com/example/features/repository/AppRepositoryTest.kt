package com.example.features.repository

import androidx.paging.PagingSource
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.core.repository.network.GithubApiService
import com.example.core.repository.paging.GithubPagingSource
import com.example.features.FakeRepositoryImpl
import com.example.features.utils.FavoriteUserDaoTest
import com.example.features.utils.PagingTestSource
import io.reactivex.Single
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.kotlin.*

@RunWith(JUnit4::class)
class AppRepositoryTest {

    @Spy
    lateinit var db : FavoriteUserDaoTest

    @Mock
    lateinit var fakeGithubApiService: GithubApiService

    @Mock
    lateinit var githubPagingSource: GithubPagingSource

    private lateinit var  repoImpl: FakeRepositoryImpl

    lateinit var closeable: AutoCloseable

    @Before
    fun setUp() {
        closeable = MockitoAnnotations.openMocks(this)
        repoImpl = spy(FakeRepositoryImpl(fakeGithubApiService, githubPagingSource,  db))
    }


    @Test
    fun testDeleteUserById(){
        repoImpl.executeDeleteUserById(0)
        then(db).should().deleteUserById(0)
    }

    @Test
    fun testDeleteFavorites(){
        repoImpl.executeDeleteAllFavorites()
        then(db).should().deleteAllUsers()
    }

    @Test
    fun testSaveFavorite(){
        val favoriteUser = FavoriteUser(1, "gbenga",
            1, "http", 1.2)
        given(repoImpl.executeFavoriteUser(favoriteUser)).willReturn(Single.just("s"))

        then(db).should().saveFavourite(FavoriteUser(1, "gbenga",
            1, "http", 1.2))
    }

    @Test
    fun testGetFavoriteUsers(){
        val githubPagingSource = PagingTestSource()

        val refreshRequest: PagingSource.LoadParams.Refresh<Int> =
            PagingSource.LoadParams.Refresh(null, 2, false)

        val mockFavoriteUsers  = arrayListOf(FavoriteUser(0, "gbenga",
            0, "http", 0.0),FavoriteUser(1, "gbenga",
            1, "http", 1.0))

        githubPagingSource.loadSingle(refreshRequest)
            .test()
            .await()
            .assertValueCount(1)
            .assertValue { it ->
                it == PagingSource.LoadResult.Page(
                    mockFavoriteUsers,
                    11,
                    12,
                    itemsBefore = 3,
                    itemsAfter = 0
                )
            }
    }

    @Test
    fun testGetFavoriteUserById(){
        given(repoImpl.executeGetFavoriteUserById(21))
            .willReturn(Single.just(FavoriteUser(1, "gbenga",
            1, "http", 1.2)))
        repoImpl.executeGetFavoriteUserById(21)
        then(db).should().getFavoriteById(21)
    }


    @After
    fun tearDown(){
        closeable.close()
    }

}