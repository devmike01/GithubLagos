package com.example.core.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.core.repository.models.favorite.FavoriteUser

@Database(version = 9, entities = [FavoriteUser::class], exportSchema = false)
abstract class GithubDatabase  : RoomDatabase(){

    abstract fun favoriteDao(): FavouriteUsersDao

    companion object {

        private const val DATABASE_NAME : String ="github.db"

        // For Singleton instantiation
        @Volatile private var instance: GithubDatabase? = null

        fun getInstance(context: Context): GithubDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): GithubDatabase {
            return Room.databaseBuilder(context,
                GithubDatabase::class.java,
                DATABASE_NAME).fallbackToDestructiveMigration()
                .build()
        }
    }
}