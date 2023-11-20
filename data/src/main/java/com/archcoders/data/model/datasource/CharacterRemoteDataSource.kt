package com.archcoders.data.model.datasource

import com.archcoders.domain.model.CharacterDO

interface CharacterRemoteDataSource {
    suspend fun getCharactersList(): List<CharacterDO>
}