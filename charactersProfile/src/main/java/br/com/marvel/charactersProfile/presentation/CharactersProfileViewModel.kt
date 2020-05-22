package br.com.marvel.charactersProfile.presentation

import androidx.lifecycle.MutableLiveData
import br.com.marvel.base.BaseViewModel
import br.com.marvel.charactersProfile.domain.CharactersProfileInteractor
import br.com.marvel.network.IOScheduler
import br.com.marvel.network.MainScheduler
import br.com.marvel.network.model.MarvelCharacter
import br.com.marvel.network.model.MarvelComic
import br.com.marvel.network.model.MarvelSeries
import io.reactivex.Scheduler
import javax.inject.Inject


class CharactersProfileViewModel @Inject constructor(
    @MainScheduler private val mainScheduler: Scheduler,
    @IOScheduler private val ioScheduler: Scheduler,
    private val interactor : CharactersProfileInteractor
) : BaseViewModel(){

    val characterLiveData = MutableLiveData<MarvelCharacter>()
    val comicsLiveData = MutableLiveData<MutableList<MarvelComic>>().apply { value = mutableListOf() }
    val seriesLiveData = MutableLiveData<MutableList<MarvelSeries>>().apply { value = mutableListOf() }

    private var seriesOffset = 0
    private var comicsOffset = 0

    override fun onCreate() {
        super.onCreate()
        getCharacter()
        getComics()
        getSeries()
    }

    fun onComicsLastItemVisible(lastItemVisible: Int) {
        comicsLiveData.value?.size
            ?.takeIf { lastItemVisible == it - 1 }
            ?.run {
                comicsOffset += DEFAULT_LIMIT
                getSeries()
            }
    }

    fun onSeriesLastItemVisible(lastItemVisible: Int) {
        seriesLiveData.value?.size
            ?.takeIf { lastItemVisible == it - 1 }
            ?.run {
                seriesOffset += DEFAULT_LIMIT
                getSeries()
            }
    }

    private fun getCharacter(){
        compositeDisposable.add(interactor.getCharacter()
            .observeOn(mainScheduler)
            .subscribeOn(ioScheduler)
            .subscribe({
                characterLiveData.value = it
            }, {
                it.printStackTrace()
            }))
    }

    private fun getComics(){
        compositeDisposable.add(interactor.getCharacterComics(DEFAULT_LIMIT, comicsOffset)
            .observeOn(mainScheduler)
            .subscribeOn(ioScheduler)
            .subscribe({
                comicsLiveData.value = comicsLiveData.value!!.apply {
                    addAll(it)
                }
            }, {
                it.printStackTrace()
            }))
    }

    private fun getSeries(){
        compositeDisposable.add(interactor.getCharacterSeries(DEFAULT_LIMIT, seriesOffset)
            .observeOn(mainScheduler)
            .subscribeOn(ioScheduler)
            .subscribe({
                seriesLiveData.value = seriesLiveData.value!!.apply {
                    addAll(it)
                }
            }, {
                it.printStackTrace()
            }))
    }

    companion object {
        private const val DEFAULT_LIMIT = 20
    }
}