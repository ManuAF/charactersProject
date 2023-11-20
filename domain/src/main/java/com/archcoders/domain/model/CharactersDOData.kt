package com.archcoders.domain.model

import java.io.Serializable

data class CharactersDOData(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<CharacterDO>?
): Serializable
