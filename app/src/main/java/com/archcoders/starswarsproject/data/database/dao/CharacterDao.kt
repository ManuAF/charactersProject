package com.archcoders.starswarsproject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.archcoders.starswarsproject.data.database.CharacterDB
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAll(): List<CharacterDB>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun findById(id: Int): CharacterDB

    @Query("SELECT COUNT(id) FROM characters")
    suspend fun characterCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characterDBS: List<CharacterDB>)

    @Update
    suspend fun updateCharacter(characterDB: CharacterDB)
}