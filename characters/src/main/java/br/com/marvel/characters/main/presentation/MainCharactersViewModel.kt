package br.com.marvel.characters.main.presentation

import br.com.marvel.base.BaseViewModel
import br.com.marvel.characters.main.di.SearchCanceledPublisher
import br.com.marvel.characters.main.di.TextSearchPublisher
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainCharactersViewModel @Inject constructor(
    @TextSearchPublisher private val publisherText: PublishSubject<String>,
    @SearchCanceledPublisher private val publisherCanceled: PublishSubject<Unit>
) : BaseViewModel() {

    fun onTextSubmit(query: String?) {
        query?.let {
            publisherText.onNext(it)
        }
    }

    fun onSearchCanceled() {
        publisherCanceled.onNext(Unit)
    }
}
