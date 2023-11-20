package com.archcoders.starswarsproject.data.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CharacterServer {

    val service: CharactersService = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com/v1/public/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().run {
            create(CharactersService::class.java)
        }
}