package com.d3if0002.currex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RateEntity::class], version = 1, exportSchema = false)
abstract class RateDB : RoomDatabase() {
    abstract val dao: RateDAO

    companion object {
        @Volatile
        private var INSTANCE: RateDB? = null

        fun getInstance(context: Context): RateDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RateDB::class.java,
                        "rate.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}