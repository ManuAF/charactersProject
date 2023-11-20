package com.archcoders.starswarsproject.data.database

import android.util.Log
import com.archcoders.data.model.datasource.CharacterLocalDataSource
import com.archcoders.domain.model.CharacterDO
import com.archcoders.starswarsproject.data.database.dao.CharacterDao
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CharacterRoomDataSource(private val characterDao: CharacterDao) : CharacterLocalDataSource {

    override suspend fun getCharacters(): List<CharacterDO> = suspendCoroutine { emitter ->
        getList(
            characterDao.getAll(),
            onSuccessList = { emitter.resume(it) }
        )
    }

    private fun getList(charactersDB: List<CharacterDB>, onSuccessList: (List<CharacterDO>) -> Unit) {
        val characters = mutableListOf<CharacterDO>()

        charactersDB.forEach {
            characters.add(it.toModel())
        }

        onSuccessList(characters)
    }

    override suspend fun isEmpty(): Boolean = characterDao.characterCount() == 0
    override suspend fun save(characterDBS: List<CharacterDO>) {
        val list = mutableListOf<CharacterDB>()
        var cont = 1
        characterDBS.forEach { characterDO ->
            list.add(
                CharacterDB(
                    cont,
                    characterDO.name ?: "",
                    characterDO.description ?: "",
                    characterDO.resourceURI ?: "",
                    favourite = false,
                    imageURL = ""
                )
            )
            cont++
        }
        characterDao.insertCharacters(list)
    }

    override suspend fun update(characterDO: CharacterDO) {
        val id = Integer.parseInt(characterDO.id ?: "")

        val characterDB = CharacterDB(
            id = id,
            name = characterDO.name?:"",
            description = characterDO.description?:"",
            resourceURI = characterDO.resourceURI?:"",
            imageURL = characterDO.imageURL?:"",
            favourite = !characterDO.favourite
        )
        switchFavourite(
            unit = characterDao.updateCharacter(characterDB),
            onSuccessUpdate = {  }
        )

    }

    private fun switchFavourite(unit: Unit, onSuccessUpdate: (Boolean) -> Unit) {
        Log.d("switchFavourite",unit.toString())
        onSuccessUpdate(true)
    }

    override suspend fun findById(characterId: Int): CharacterDO = suspendCoroutine { emitter ->

        getCharacterDB(
            characterDB = characterDao.findById(characterId),
            onSuccessCharacterDO = { emitter.resume(it) })
    }

    private fun getCharacterDB(characterDB: CharacterDB, onSuccessCharacterDO: (CharacterDO) -> Unit) {
        onSuccessCharacterDO(characterDB.toModel())
    }
}