package com.archcoders.starswarsproject

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.repository.RetrofitManager
import com.archcoders.starswarsproject.services.PeopleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ListCharacterViewModel:ViewModel() {

    private val characters: MutableLiveData<MutableList<CharacterEntity>> = MutableLiveData()
    var _characters: LiveData<MutableList<CharacterEntity>> = characters

    suspend fun createUserList() {
        coroutineScope {
            RetrofitManager.retrofit.create(PeopleService::class.java).getPeopleList()
                .body()?.apply {
                    this.data?.let {
                        it.results?.let {
                            characters.value = it as MutableList<CharacterEntity>
                        }
                    }
                }
        }

    }


}