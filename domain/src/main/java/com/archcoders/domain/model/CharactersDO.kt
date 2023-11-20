package com.archcoders.domain.model

import java.io.Serializable

data class CharactersDO(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val data: CharactersDOData?
):Serializable
