package com.archcoders.data

import com.archcoders.data.model.datasource.CharacterLocalDataSource
import com.archcoders.data.model.datasource.CharacterRemoteDataSource
import com.archcoders.data.repository.CharacterRepository
import com.archcoders.domain.model.CharacterDO
import com.archcoders.domain.model.ComicDO
import com.archcoders.domain.model.ThumbnailDO
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryTest {

    @Mock
    lateinit var localDataSource: CharacterLocalDataSource

    @Mock
    lateinit var remoteDataSource: CharacterRemoteDataSource

    @Mock
    lateinit var characterRepository: CharacterRepository

    private val localCharacters = listOf(sampleCharacter.copy("1"))

    @Before
    fun setUp() {
        whenever(localDataSource.getCharacters()).thenReturn(localCharacters)

        characterRepository = CharacterRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `Popular characters are taken from local data source if available`(): Unit = runBlocking {

        val result = characterRepository.getPopularCharacters()

        assert(localCharacters, result)
    }

    @Test
    fun `Switching favourite mark as favourite an unfavourite character`() = runBlocking {
        val updatedCharacter= sampleCharacter.copy(favourite = false)
        characterRepository.update(updatedCharacter)
    }
}

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