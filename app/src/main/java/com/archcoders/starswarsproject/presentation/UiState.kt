package com.archcoders.starswarsproject.presentation

import com.archcoders.domain.model.CharacterDO
import com.archcoders.starswarsproject.data.model.ErrorModel


data class UiState(
    val loading: Boolean = false,
    val characterDBS: List<CharacterDO>? = null,
    val error: ErrorModel? = null
)
