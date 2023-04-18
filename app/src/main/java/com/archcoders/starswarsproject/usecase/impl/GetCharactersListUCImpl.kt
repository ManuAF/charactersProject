package com.archcoders.starswarsproject.usecase.impl

import com.archcoders.starswarsproject.entities.CharactersEntity
import com.archcoders.starswarsproject.repository.RetrofitManager
import com.archcoders.starswarsproject.services.CharactersService
import com.archcoders.starswarsproject.usecase.GetCharactersListUC
import retrofit2.Response

class GetCharactersListUCImpl(): GetCharactersListUC {
    override suspend fun getCharactersList(): Response<CharactersEntity> {
        return RetrofitManager.retrofit.create(CharactersService::class.java).getPeopleList()
    }
}