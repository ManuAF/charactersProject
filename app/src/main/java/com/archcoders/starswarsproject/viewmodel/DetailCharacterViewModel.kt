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