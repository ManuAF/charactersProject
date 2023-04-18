package com.archcoders.starswarsproject.viewmodel

import androidx.lifecycle.*
import com.archcoders.starswarsproject.entities.CharacterEntity
import com.archcoders.starswarsproject.usecase.impl.GetCharactersListUCImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListCharacterViewModel(private val getCharactersListUCImpl: GetCharactersListUCImpl) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            getCharactersListUCImpl.getCharactersList().body()?.apply {
                _state.value = UiState(loading = true)
                _state.value = UiState(characters = this.data?.results)
            }

        }
    }

    fun onCharacterClick(characterEntity: CharacterEntity) {
        _state.value = _state.value.copy(navigateTo = characterEntity)
    }


    data class UiState(
        val loading: Boolean = false,
        val characters: List<CharacterEntity>? = null,
        val navigateTo: CharacterEntity? = null
    )
}