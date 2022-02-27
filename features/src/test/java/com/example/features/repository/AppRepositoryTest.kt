package com.example.features.repository

import com.example.core.repository.GithubRepository
import com.example.core.repository.GithubRepositoryImpl
import com.example.core.repository.models.favorite.FavoriteUser
import com.example.features.FakeGithubApiService
import com.example.features.FakeRepository
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import java.util.function.Predicate

@RunWith(JUnit4::class)
class AppRepositoryTest {

    //@Mock
    lateinit var repository: FakeRepository

    lateinit var fakeGithubApiService: FakeGithubApiService

    lateinit var fakeDaoTest: FavoriteUserDaoTest

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
        fakeGithubApiService = FakeGithubApiService()
        fakeDaoTest = FavoriteUserDaoTest()

        repository = FakeRepository(fakeGithubApiService,
            fakeDaoTest)
    }

    @Test
    fun testDeleteUserById(){
        val testScheduler = TestScheduler()
        val testSubscriber = TestSubscriber<String>()

        var result : String? =""

        val observer = repository
            .executeSaveFavoriteUser(FavoriteUserDaoTest.getFavorite(10))
            .observeOn(testScheduler)
            .subscribeOn(testScheduler)
            .test()
        testSubscriber.assertValue("---")
        observer.dispose()
      //  observer.subscribe()
//            .test()
//
//        observer.assertSubscribed()
//            .assertNotTerminated()
//            .assertNoErrors()


       // assertEquals(result, "This was a fake success message")
    }
}