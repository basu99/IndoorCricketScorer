package com.example.indoorcricketscorer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.indoorcricketscorer.data.dao.MatchDao
import com.example.indoorcricketscorer.data.entity.MatchEntity

@Database(
    entities = [MatchEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun matchDao(): MatchDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "indoor_cricket_scorer_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
