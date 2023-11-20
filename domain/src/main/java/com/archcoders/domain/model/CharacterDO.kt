package com.archcoders.domain.model

import java.io.Serializable


data class CharacterDO(
    val id: String?=null,
    val name: String?=null,
    val description: String?=null,
    val thumbnail: ThumbnailDO?=null,
    val resourceURI: String?=null,
    val imageURL: String?=null,
    val comics: ComicDO?=null,
    val favourite: Boolean = false,
): Serializable

