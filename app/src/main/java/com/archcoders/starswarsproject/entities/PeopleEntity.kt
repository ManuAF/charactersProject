package com.archcoders.starswarsproject.entities

import com.archcoders.starswarsproject.entities.CharacterEntity

data class PeopleEntity(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<CharacterEntity>?
)