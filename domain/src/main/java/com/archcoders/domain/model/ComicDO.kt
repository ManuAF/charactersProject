package com.archcoders.domain.model

import java.io.Serializable


data class ComicDO(
    val collectionURI: String?,
    val items: List<ItemComicDO>
): Serializable
