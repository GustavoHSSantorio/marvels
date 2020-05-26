package br.com.marvel.characterData.data

import br.com.marvel.characterData.data.model.MarvelCharacterDataContainer
import br.com.marvel.characterData.data.model.MarvelCharacterDataWrapper
import br.com.marvel.network.model.MarvelCharacter

internal const val characterId  = 1
internal const val name  = "name"
internal const val limit  = 1
internal const val offset  = 1
internal val characterMarvel = MarvelCharacter()
internal val characterList  = listOf(characterMarvel)
internal val characterDataContainer  = MarvelCharacterDataContainer(characterList)
internal val characterDataMarvelWrapper  = MarvelCharacterDataWrapper(characterDataContainer)