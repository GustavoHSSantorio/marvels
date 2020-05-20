package br.com.marvel.comics.domain

import br.com.marvel.comicData.data.ComicRepository
import br.com.marvel.network.model.MarvelComic
import io.reactivex.Single
import javax.inject.Inject

interface ComicListInteractor {
    fun getComics(name: String?, limit: Int, offset: Int): Single<List<MarvelComic>>
}

class ComicListInteractorImp @Inject constructor(private val repository : ComicRepository) : ComicListInteractor{
    override fun getComics(name: String?, limit: Int, offset: Int): Single<List<MarvelComic>> =
        (name?.let {
            repository.getComicsTitleStartsWith(limit, offset, it)
        } ?: run {
            repository.getComicsList(limit, offset)
        }).map {
            it.results
        }
}