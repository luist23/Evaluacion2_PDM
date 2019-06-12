package me.nelsoncastro.pokeapichingona.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.nelsoncastro.pokeapichingona.Database.Domain.MovieDao
import me.nelsoncastro.pokeapichingona.Models.Movie

@Database(entities = [Movie::class], version = 2, exportSchema = false)
abstract class MainDatabase : RoomDatabase(){

    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(appContext: Context): MainDatabase {
            if (INSTANCE == null) {
                synchronized(MainDatabase::class) {
                INSTANCE = Room
                        .databaseBuilder(appContext.applicationContext
                                , MainDatabase::class.java
                                ,"movies_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}