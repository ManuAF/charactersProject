package com.archcoders.data.repository

import com.archcoders.data.model.datasource.CharacterLocalDataSource
import com.archcoders.data.model.datasource.CharacterRemoteDataSource
import com.archcoders.domain.model.CharacterDO
import java.util.*
import kotlin.coroutines.suspendCoroutine

class CharacterRepository(
    private val localDataSource: CharacterLocalDataSource,
    private val remoteDataSource: CharacterRemoteDataSource
) {

    suspend fun getPopularCharacters(): List<CharacterDO> {
        if (localDataSource.isEmpty()) {
            val characters = remoteDataSource.getCharactersList()
            localDataSource.save(characters)
        }

        return localDataSource.getCharacters()
    }

    suspend fun refreshCharacters(): List<CharacterDO>  {
        val characters = remoteDataSource.getCharactersList()
//        localDataSource.save(characters)
//
        val list = localDataSource.getCharacters()
        return list
    }

    suspend fun findById(characterId: Int) = localDataSource.findById(characterId)

    suspend fun update(characterDO: CharacterDO) = localDataSource.update(characterDO)


}
