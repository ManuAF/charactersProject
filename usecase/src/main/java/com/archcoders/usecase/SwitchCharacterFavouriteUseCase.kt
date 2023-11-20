package com.archcoders.usecase

import com.archcoders.data.repository.CharacterRepository
import com.archcoders.domain.model.CharacterDO


class SwitchCharacterFavouriteUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(characterDO: CharacterDO) = with(characterDO) {
        copy(favourite = !this.favourite).also { repository.update(characterDO) }
    }
}