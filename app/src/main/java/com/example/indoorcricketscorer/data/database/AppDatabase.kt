package com.example.indoorcricketscorer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.indoorcricketscorer.data.dao.MatchDao
import com.example.indoorcricketscorer.data.entity.MatchEntity
import com.example.indoorcricketscorer.data.entity.PlayerEntity
import com.example.indoorcricketscorer.data.dao.PlayerDao
@Database(
    entities = [

        MatchEntity::class,

        PlayerEntity::class

    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun matchDao(): MatchDao

    abstract fun playerDao(): PlayerDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "indoor_cricket_scorer_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
