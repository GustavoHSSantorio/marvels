package br.com.marvel.comicData.data

import br.com.marvel.comicData.data.model.MarvelComicDataContainer
import br.com.marvel.comicData.data.model.MarvelSeriesContainer
import io.reactivex.Single
import javax.inject.Inject

interface ComicRepository {
    fun getComicsList(limit: Int = 20, offset: Int = 0): Single<MarvelComicDataContainer>
    fun getComicsTitleStartsWith(limit: Int = 20, offset: Int = 0, startsWith: String = ""): Single<MarvelComicDataContainer>
    fun getComic(id: Int): Single<MarvelComicDataContainer>
    fun getCharacterComics(id: Int, limit : Int = 20, offset : Int = 0): Single<MarvelComicDataContainer>
    fun getCharacterSeries(id: Int, limit : Int = 20, offset : Int = 0): Single<MarvelSeriesContainer>
}

class ComicRepositoryImp @Inject constructor(private val service: ComicService) :
    ComicRepository {

    override fun getComicsList(
        limit: Int,
        offset: Int
    ): Single<MarvelComicDataContainer> =
        service.getComics(limit, offset)
            .map {
                it.data
            }

    override fun getComicsTitleStartsWith(
        limit: Int,
        offset: Int,
        startsWith: String
    ): Single<MarvelComicDataContainer> =
        service.getComicsBeginLetter(limit, offset, startsWith)
            .map {
                it.data
            }

    override fun getComic(
        id: Int
    ): Single<MarvelComicDataContainer> =
        service.getComic(id)
            .map {
                it.data
            }

    override fun getCharacterComics(
        id: Int,
        limit: Int,
        offset: Int
    ): Single<MarvelComicDataContainer> =
        service.getCharacterComics(id, limit, offset)
            .map {
                it.data
            }

    override fun getCharacterSeries(
        id: Int,
        limit: Int,
        offset: Int
    ): Single<MarvelSeriesContainer> =
        service.getCharacterSeries(id, limit, offset)
            .map {
                it.data
            }
}
