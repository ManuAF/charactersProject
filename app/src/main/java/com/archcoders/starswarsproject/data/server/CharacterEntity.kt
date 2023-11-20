package com.archcoders.starswarsproject.data.server

import com.archcoders.starswarsproject.data.model.ComicEntity
import com.archcoders.starswarsproject.data.model.ThumbnailEntity


data class CharacterEntity(
    val id: String?,
    val name: String?,
    val description: String?,
    val thumbnail: ThumbnailEntity,
    val resourceURI: String?,
    val comics: ComicEntity?
)

