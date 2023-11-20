package com.archcoders.usecase

import com.archcoders.data.repository.CharacterRepository


class GetCharactersUseCase(private val repository: CharacterRepository) {

    suspend fun getCharacters() = repository.getPopularCharacters()
}