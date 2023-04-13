package com.archcoders.starswarsproject.services

import com.archcoders.starswarsproject.utils.Constants
import com.archcoders.starswarsproject.entities.CharactersEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PeopleService {
    @Headers("Content-Type: application/json")
    @GET("characters")
    suspend fun getPeopleList(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.ts,
        @Query("hash") hash: String = Constants.hash(),
    ): Response<CharactersEntity>
}