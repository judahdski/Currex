package com.d3if0002.currex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        RateEntity::class,
        ConversionEntity::class
    ],
    version = 3, exportSchema = false
)
abstract class ExchangeDB : RoomDatabase() {
    abstract val dao: ExchangeDAO

    companion object {
        @Volatile
        private var INSTANCE: ExchangeDB? = null

        fun getInstance(context: Context): ExchangeDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExchangeDB::class.java,
                        "exchange.db"
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