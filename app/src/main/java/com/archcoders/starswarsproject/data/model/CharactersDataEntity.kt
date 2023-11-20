package com.archcoders.starswarsproject.data.model

import com.archcoders.starswarsproject.data.server.CharacterEntity

data class CharactersDataEntity(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<CharacterEntity>?
)
