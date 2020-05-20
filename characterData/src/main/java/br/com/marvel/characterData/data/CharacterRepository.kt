package br.com.marvel.characterData.data

import br.com.marvel.characterData.data.model.MarvelCharacterDataContainer
import io.reactivex.Single
import javax.inject.Inject

interface CharacterRepository {
    fun getCharacterList(limit: Int = 20, offset: Int = 0): Single<MarvelCharacterDataContainer>
    fun getCharacterNameStartsWith(limit: Int = 20, offset: Int = 0, startsWith: String = ""): Single<MarvelCharacterDataContainer>
    fun getCharacter(id: Int): Single<MarvelCharacterDataContainer>
}

class CharacterRepositoryImp @Inject constructor(private val service: CharacterService) :
    CharacterRepository {

    override fun getCharacterList(
        limit: Int,
        offset: Int
    ): Single<MarvelCharacterDataContainer> =
        service.getCharacters(limit, offset)
            .map {
                it.data
            }

    override fun getCharacterNameStartsWith(
        limit: Int,
        offset: Int,
        startsWith: String
    ): Single<MarvelCharacterDataContainer> =
        service.getCharactersBeginLetter(limit, offset, startsWith)
            .map {
                it.data
            }

    override fun getCharacter(
        id: Int
    ): Single<MarvelCharacterDataContainer> =
        service.getCharacter(id)
            .map {
                it.data
            }
}
