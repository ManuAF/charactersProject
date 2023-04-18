package com.archcoders.starswarsproject.model

import com.archcoders.starswarsproject.entities.CharacterEntity

data class UiState(
    val loading: Boolean = false,
    val characters: List<CharacterEntity>? = null,
    val navigateTo: CharacterEntity? = null
)
