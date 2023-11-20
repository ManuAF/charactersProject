package com.archcoders.domain.model

import java.io.Serializable

data class PeopleDO(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<CharacterDO>?
):Serializable