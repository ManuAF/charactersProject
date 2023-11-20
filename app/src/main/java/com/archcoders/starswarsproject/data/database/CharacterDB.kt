package com.archcoders.starswarsproject.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.archcoders.domain.model.CharacterDO
import java.io.Serializable

@Entity(tableName = "characters")
data class CharacterDB(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val name: String,
    val description: String,
    val resourceURI: String,
    val imageURL: String,
    val favourite: Boolean
) : Serializable

fun CharacterDB.toModel() =
    CharacterDO(
        id = id.toString(),
        name = name,
        description = description,
        resourceURI = resourceURI,
        comics = null,
        favourite = favourite,
    )
