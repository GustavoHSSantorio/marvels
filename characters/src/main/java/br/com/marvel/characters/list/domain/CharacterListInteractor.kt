package br.com.marvel.characters.list.domain

import br.com.marvel.characterData.data.CharacterRepository
import br.com.marvel.network.model.MarvelCharacter
import io.reactivex.Single
import javax.inject.Inject

interface CharacterListInteractor{
    fun getCharacters(limit: Int = 20, offset: Int = 0) : Single<List<MarvelCharacter>>
}

class CharacterListInteractorImp @Inject constructor(private val repository: CharacterRepository) : CharacterListInteractor{
    override fun getCharacters(limit: Int, offset: Int): Single<List<MarvelCharacter>> =
        repository.getCharacterList(limit, offset)
            .map {
                it.results
            }
}
