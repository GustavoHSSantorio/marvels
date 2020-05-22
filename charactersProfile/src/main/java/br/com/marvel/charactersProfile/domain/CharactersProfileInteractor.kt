package br.com.marvel.charactersProfile.domain

import br.com.marvel.characterData.data.CharacterRepository
import br.com.marvel.charactersProfile.di.CharacterId
import br.com.marvel.comicData.data.ComicRepository
import br.com.marvel.network.model.MarvelCharacter
import br.com.marvel.network.model.MarvelComic
import br.com.marvel.network.model.MarvelSeries
import io.reactivex.Single
import javax.inject.Inject

interface CharactersProfileInteractor {
    fun getCharacter(): Single<MarvelCharacter>
    fun getCharacterComics(limit: Int = 20, offset: Int = 0): Single<List<MarvelComic>>
    fun getCharacterSeries(limit: Int = 20, offset: Int = 0): Single<List<MarvelSeries>>
}

class CharactersProfileInteractorImp @Inject constructor(
    @CharacterId private val characterId: Int?,
    private val characterRepository: CharacterRepository,
    private val comicRepository: ComicRepository
) : CharactersProfileInteractor {

    override fun getCharacter(): Single<MarvelCharacter> =
        characterRepository.getCharacter(characterId ?: 0).map {
            it.results?.firstOrNull()
        }

    override fun getCharacterComics(limit: Int, offset: Int): Single<List<MarvelComic>> =
        comicRepository.getCharacterComics(characterId ?: 0, limit, offset)
            .map {
                it.results
            }

    override fun getCharacterSeries(limit: Int, offset: Int): Single<List<MarvelSeries>> =
        comicRepository.getCharacterSeries(characterId ?: 0, limit, offset)
            .map {
                it.results
            }
}
