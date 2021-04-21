package com.prod.treknation.crs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CRSUserInfo::class, Language::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun crsUserInfoDao(): CRSUserInfoDao
    abstract fun languageDao(): LanguageDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
                AppDatabase::class.java, "CRS_USER.db")
                .allowMainThreadQueries()
                .build()

    }
}

