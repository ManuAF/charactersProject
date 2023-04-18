package com.archcoders.starswarsproject.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.viewmodel.DetailCharacterViewModel


@Suppress("UNCHECKED_CAST")
class DetailCharacterViewModelFactory(private val character: CharacterEntity) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailCharacterViewModel(character) as T
    }
}