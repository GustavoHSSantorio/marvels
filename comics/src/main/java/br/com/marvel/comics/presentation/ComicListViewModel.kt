package br.com.marvel.comics.presentation

import androidx.lifecycle.MutableLiveData
import br.com.marvel.base.BaseViewModel
import br.com.marvel.comics.di.ComicSearchCanceledPublisher
import br.com.marvel.comics.di.ComicTextSearchPublisher
import br.com.marvel.comics.domain.ComicListInteractor
import br.com.marvel.network.IOScheduler
import br.com.marvel.network.MainScheduler
import br.com.marvel.network.model.MarvelComic
import io.reactivex.Scheduler
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

enum class ComicListState {
    ShowSuccessView,
    ShowErrorView,
    ShowLoading,
    RemoveLoading
}

class ComicListViewModel @Inject constructor(
    private val interactor: ComicListInteractor,
    @MainScheduler private val mainScheduler: Scheduler,
    @IOScheduler private val ioScheduler: Scheduler,
    @ComicTextSearchPublisher private val publisher: PublishSubject<String>,
    @ComicSearchCanceledPublisher private val publisherCanceled: PublishSubject<Unit>
) : BaseViewModel() {

    val comicsLiveData = MutableLiveData<MutableList<MarvelComic>>().apply { value = mutableListOf() }
    val stateLiveData = MutableLiveData<ComicListState>()

    private var offset = 0
    private var name: String? = null

    override fun onCreate() {
        super.onCreate()
        subscribeSearch()
        getComics()
    }

    fun retry() {
        getComics()
    }

    fun onLastItemVisible(lastItemVisible: Int) {
        comicsLiveData.value?.size
            ?.takeIf { lastItemVisible == it - 1 }
            ?.run {
                offset += DEFAULT_LIMIT
                getComics()
            }
    }

    private fun subscribeSearch() {
        compositeDisposable.add(
            publisher.subscribe({
                name = it
                offset = 0
                comicsLiveData.value = mutableListOf()
                getComics()
            }, { it.printStackTrace() }))

        compositeDisposable.add(
            publisherCanceled.subscribe({
                name = null
                offset = 0
                comicsLiveData.value = mutableListOf()
                getComics()
            }, {
                it.printStackTrace()
            }))
    }

    private fun getComics() {
        compositeDisposable.add(
            interactor.getComics(name = name, limit = DEFAULT_LIMIT, offset = offset)
                .observeOn(mainScheduler)
                .subscribeOn(ioScheduler)
                .doOnSubscribe {
                    if (comicsLiveData.value.isNullOrEmpty())
                        stateLiveData.value = ComicListState.ShowLoading
                }
                .doFinally {
                    stateLiveData.value = ComicListState.RemoveLoading
                }
                .doOnSuccess {
                    stateLiveData.value = ComicListState.ShowSuccessView
                }
                .subscribe({
                    comicsLiveData.value = comicsLiveData.value!!.apply {
                        addAll(it)
                    }
                }, {
                    it.printStackTrace()
                    stateLiveData.value = ComicListState.ShowErrorView
                })
        )
    }

    companion object {
        private const val DEFAULT_LIMIT = 20
    }

}