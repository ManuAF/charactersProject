package com.archcoders.usecase

import com.archcoders.data.repository.CharacterRepository


class FindCharacterUseCase(private val repository: CharacterRepository) {
    suspend fun getCharactersFromDataSource(characterId: Int) = repository.findById(characterId = characterId)
}