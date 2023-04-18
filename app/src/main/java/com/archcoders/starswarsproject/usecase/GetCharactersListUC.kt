package com.archcoders.starswarsproject.usecase

import com.archcoders.starswarsproject.entities.CharactersEntity
import retrofit2.Response

interface GetCharactersListUC {
    suspend fun getCharactersList(): Response<CharactersEntity>
}