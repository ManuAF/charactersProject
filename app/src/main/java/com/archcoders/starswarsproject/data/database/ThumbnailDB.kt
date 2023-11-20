package com.archcoders.starswarsproject.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.archcoders.domain.model.ThumbnailDO

@Entity
data class ThumbnailDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val path: String,
    val extension: String
)

fun ThumbnailDB.toModel() =
    ThumbnailDO(
        path = path,
        extension = extension

    )
