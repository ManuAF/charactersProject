package com.archcoders.starswarsproject.data.server

import com.archcoders.starswarsproject.data.model.CharactersEntity
import com.archcoders.starswarsproject.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CharactersService {
    @Headers("Content-Type: application/json")
    @GET("characters")
    suspend fun getPeopleList(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.ts,
        @Query("hash") hash: String = Constants.hash(),
    ): Response<CharactersEntity>
}