package com.example.core.di

import android.app.Application
import com.example.core.repository.GithubRepository
import com.example.core.repository.GithubRepositoryImpl
import com.example.core.repository.database.FavouriteUsersDao
import com.example.core.repository.database.GithubDatabase
import com.example.core.repository.network.GithubApiService
import com.example.core.repository.paging.GithubPagingSource
import com.example.core.repository.rx.CoreSchedulers
import com.example.core.repository.rx.CoreSchedulersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level


@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    fun provideOkHttpClient() :OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();
    }

    @Provides
    fun provideCoreSchedulers(coreSchedulersImpl: CoreSchedulersImpl): CoreSchedulers{
        return coreSchedulersImpl
    }


    @Provides
    fun provideGithubService(okHttpClient: OkHttpClient): GithubApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(GithubApiService::class.java)
    }

    @Provides
    fun provideFavorite(applicationContext: Application):
            FavouriteUsersDao = GithubDatabase.getInstance(applicationContext).favoriteDao()

    @Provides
    fun provideGithubRepository(favoriteDao: FavouriteUsersDao,
                                githubPagingSource: GithubPagingSource,
                                commonSchedulers: CoreSchedulers,
                                service: GithubApiService): GithubRepository{
        return GithubRepositoryImpl(apiService = service, favoriteDao = favoriteDao,
            githubPagingSource = githubPagingSource, schedulers = commonSchedulers)
    }

}