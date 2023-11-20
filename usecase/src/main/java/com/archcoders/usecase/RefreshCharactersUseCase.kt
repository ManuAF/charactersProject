package com.archcoders.usecase

import com.archcoders.data.repository.CharacterRepository


class RefreshCharactersUseCase(private val repository : CharacterRepository) {

    suspend operator fun invoke() = repository.refreshCharacters()
}