package com.archcoders.starswarsproject.entities

data class CharactersDataEntity(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<CharacterEntity>?
)
