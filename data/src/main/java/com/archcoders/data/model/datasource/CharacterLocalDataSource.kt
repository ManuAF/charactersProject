package com.archcoders.data.model.datasource

import com.archcoders.domain.model.CharacterDO

interface CharacterLocalDataSource {
    suspend fun getCharacters(): List<CharacterDO>
    suspend fun isEmpty(): Boolean
    suspend fun save(characterDBS: List<CharacterDO>)
    suspend fun findById(characterId: Int): CharacterDO

    suspend fun update(characterDO: CharacterDO)
}