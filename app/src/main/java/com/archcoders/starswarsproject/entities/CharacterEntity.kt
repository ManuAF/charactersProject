package com.archcoders.starswarsproject.entities

data class CharacterEntity(
    val id: String?,
    val name: String?,
    val description: String?,
    val thumbnail: ThumbnailEntity?,
    val resourceURI: String?,
    val comics : ComicEntity?
)
