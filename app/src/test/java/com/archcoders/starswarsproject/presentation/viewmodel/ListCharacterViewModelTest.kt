package com.archcoders.starswarsproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archcoders.domain.model.CharacterDO
import com.archcoders.starswarsproject.presentation.UiState
import com.archcoders.usecase.GetCharactersUseCase
import com.archcoders.usecase.RefreshCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListCharacterViewModelTest{


    private val sampleCharacter = CharacterDO(
        id = "0",
        name = "Title",
        description = "Lorem Impsum Dolot",
        thumbnail = null,
        resourceURI = "",
        imageURL = "",
        comics = null,
        favourite = false
    )

    private lateinit var vm: ListCharacterViewModel

    private lateinit var requestCharactersUseCase: RefreshCharactersUseCase

    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private var characters = listOf(sampleCharacter.copy(id = "1"))
    @Before
    fun setUp(){
        whenever(getCharactersUseCase).thenReturn(characters)
        vm = ListCharacterViewModel(requestCharactersUseCase,getCharactersUseCase)
    }
    @Test
    fun `State is updated with current cached content inmediately`() = runTest {

        vm.state.collect{
            assertEquals(UiState(characterDBS = characters),it)
        }

    }
}