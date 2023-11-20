package com.archcoders.starswarsproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archcoders.domain.model.CharacterDO
import com.archcoders.starswarsproject.databinding.FragmentDetailBinding
import com.archcoders.usecase.FindCharacterUseCase
import com.archcoders.usecase.RefreshCharactersUseCase
import com.archcoders.usecase.SwitchCharacterFavouriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailCharacterViewModel(
    private val findCharacterUseCase: FindCharacterUseCase,
    private val refreshCharactersUseCase: RefreshCharactersUseCase,
    private val switchCharacterFavouriteUseCase: SwitchCharacterFavouriteUseCase
) : ViewModel() {
    class UiState(
        val characterDO: CharacterDO? = null,
        val error: Error? = null,
        val favourite: Boolean = false
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
    private val characterID = MutableLiveData(0)
    val id: LiveData<Int> = characterID

    fun initializeCharacter(characterId: Int) {
        characterID.value = characterId
    }

    fun getCharacterDO() {
        viewModelScope.launch(Dispatchers.IO) {
            findCharacterUseCase.getCharactersFromDataSource(characterID.value ?: 0).let {
                _state.value = UiState(characterDO = it)
            }
        }
    }

    fun onFavouriteClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value.characterDO?.let { characterDO ->
                switchCharacterFavouriteUseCase(characterDO)
                _state.update { UiState(characterDO = characterDO.copy(favourite = !characterDO.favourite), favourite = !it.favourite) }
                refreshCharactersUseCase.invoke()
            }
        }
    }


}