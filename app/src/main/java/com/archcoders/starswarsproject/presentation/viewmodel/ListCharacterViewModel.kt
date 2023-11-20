package com.archcoders.starswarsproject.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archcoders.domain.model.CharacterDO
import com.archcoders.starswarsproject.data.database.CharacterDB
import com.archcoders.starswarsproject.presentation.UiState
import com.archcoders.usecase.GetCharactersUseCase
import com.archcoders.usecase.RefreshCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListCharacterViewModel(
    private val requestCharactersUseCase: RefreshCharactersUseCase,
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent> { }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCharactersUseCase.getCharacters().let { characters ->
                _state.update { UiState(characterDBS = characters, error = it.error) }
            }
        }
    }

    sealed interface UiEvent {
        data class NavigateTo(val character: CharacterDO) : UiEvent
    }


    fun onUiReady() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = requestCharactersUseCase()
            _state.update { it.copy(characterDBS = list) }
        }

    }

    fun onCharacterClicked(characterDO: CharacterDO) {
        viewModelScope.launch {
            _events.send(UiEvent.NavigateTo(characterDO))
        }
    }

}