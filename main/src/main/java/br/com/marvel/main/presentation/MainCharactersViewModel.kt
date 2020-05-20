package br.com.marvel.main.presentation

import br.com.marvel.base.BaseViewModel
import br.com.marvel.characters.list.di.CharactersSearchCanceledPublisher
import br.com.marvel.characters.list.di.CharactersTextSearchPublisher
import br.com.marvel.comics.di.ComicSearchCanceledPublisher
import br.com.marvel.comics.di.ComicTextSearchPublisher
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainCharactersViewModel @Inject constructor(
    @CharactersTextSearchPublisher private val charactersPublisherText: PublishSubject<String>,
    @CharactersSearchCanceledPublisher private val charactersPublisherCanceled: PublishSubject<Unit>,
    @ComicTextSearchPublisher private val comicPublisherText: PublishSubject<String>,
    @ComicSearchCanceledPublisher private val comicPublisherCanceled: PublishSubject<Unit>
) : BaseViewModel() {

    fun onTextSubmit(query: String?) {
        query?.let {
            charactersPublisherText.onNext(it)
            comicPublisherText.onNext(it)
        }
    }

    fun onSearchCanceled() {
        charactersPublisherCanceled.onNext(Unit)
        comicPublisherCanceled.onNext(Unit)
    }
}
