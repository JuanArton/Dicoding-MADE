package com.juanarton.core.repo.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dbDao(): DBDao
}