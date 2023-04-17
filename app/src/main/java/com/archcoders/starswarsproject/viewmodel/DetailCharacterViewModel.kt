package com.archcoders.starswarsproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.archcoders.starswarsproject.entities.CharacterEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailCharacterViewModel(character: CharacterEntity) : ViewModel() {

    class UiState(val character: CharacterEntity)

    private val _state = MutableStateFlow(UiState(character))
    val state: StateFlow<UiState> = _state.asStateFlow()

}

@Suppress("UNCHECKED_CAST")
class DetailCharacterViewModelFactory(private val character: CharacterEntity) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailCharacterViewModel(character) as T
    }
}