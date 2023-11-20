package com.archcoders.starswarsproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.archcoders.starswarsproject.data.database.dao.CharacterDao

@Database(entities = [CharacterDB::class, ThumbnailDB::class], version = 1, exportSchema = false)
abstract class CharacterDataBase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}