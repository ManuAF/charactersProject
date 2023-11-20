package com.archcoders.starswarsproject.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ComicEntity(
    val collectionURI: String?,
    val items: List<ItemComicEntity>
): Parcelable
