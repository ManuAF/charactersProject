package com.archcoders.starswarsproject.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemComicEntity(
    val resourceURI: String?,
    val name: String?
): Parcelable
