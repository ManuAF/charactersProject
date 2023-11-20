package com.archcoders.starswarsproject.data.model

import com.archcoders.starswarsproject.data.server.CharacterEntity

data class PeopleEntity(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<CharacterEntity>?
)