package br.com.marvel.characters.list.domain

import br.com.marvel.characterData.data.CharacterRepository
import br.com.marvel.network.model.MarvelCharacter
import io.reactivex.Single
import javax.inject.Inject

interface CharacterListInteractor {
    fun getCharacters(name: String? = null, limit: Int = 20, offset: Int = 0): Single<List<MarvelCharacter>>
}

class CharacterListInteractorImp @Inject constructor(private val repository: CharacterRepository) : CharacterListInteractor {
    override fun getCharacters(name: String?, limit: Int, offset: Int): Single<List<MarvelCharacter>> =
        (name?.let {
            repository.getCharacterNameStartsWith(limit, offset, it)
        } ?: run {
            repository.getCharacterList(limit, offset)
        }).map {
            it.results
        }
}
