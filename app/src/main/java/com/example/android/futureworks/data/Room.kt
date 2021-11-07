package com.example.android.futureworks.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.futureworks.models.Article

@Dao
interface FutureWorksDao {
    // Loads all articles
    @Query("SELECT * FROM article ORDER BY id ASC")
    fun getArticles(): LiveData<List<Article>>

    //Store values in cache
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(countries: List<Article>)
}

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class FutureWorksDatabase: RoomDatabase() {
    abstract val futureWorksDao: FutureWorksDao

    //companion object allows clients to access the methods for creating or getting the database without instantiating the class
    companion object{

        //@Volatile annotation means value of variable is always up to date and same to all execution threads
        //Value of a volatile variable will never be cached and all writes and reads will be done to and from the main memory
        //It means changes made by one thread to INSTANCE are visible to all other threads immediately.

        @Volatile
        private var INSTANCE: FutureWorksDatabase? = null

        fun getDatabase(context: Context): FutureWorksDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FutureWorksDatabase::class.java,
                        "starling_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE =instance
                }

                return instance
            }
        }
    }
}
