package com.archcoders.starswarsproject.data.server

import com.archcoders.data.model.datasource.CharacterRemoteDataSource
import com.archcoders.domain.model.CharacterDO
import com.archcoders.domain.model.ThumbnailDO
import com.archcoders.starswarsproject.data.model.ThumbnailEntity

class CharacterServerDataSource : CharacterRemoteDataSource {

    override suspend fun getCharactersList(): List<CharacterDO> {
        var serverCharacters = listOf<CharacterDO>()
        val response = CharacterServer.service.getPeopleList()
        if (response.isSuccessful) {
            val charactersResult = response.body()?.data?.results
            if (charactersResult != null) {
                serverCharacters = charactersResult.map(CharacterEntity::toDomainCharacter)
            }
        }
        return serverCharacters
    }
}

private fun CharacterEntity.toDomainCharacter() =
    CharacterDO(
        id = id, name = name, description = description, thumbnail = thumbnail.toDomainCharacter(),
        resourceURI = "${thumbnail.path}.${thumbnail.extension}", comics = null, favourite = false
    )

private fun ThumbnailEntity.toDomainCharacter() =
    ThumbnailDO(
        path = path ?: "",
        extension = extension ?: ""
    )