package com.archcoders.starswarsproject.data.model

import android.os.Parcelable
import com.archcoders.domain.model.ThumbnailDO
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThumbnailEntity(
    val path: String?,
    val extension: String?
) : Parcelable


fun ThumbnailEntity.toThumbnail(): ThumbnailDO {
    return ThumbnailDO(
        this.path ?: "",
        this.extension ?: ""
    )
}