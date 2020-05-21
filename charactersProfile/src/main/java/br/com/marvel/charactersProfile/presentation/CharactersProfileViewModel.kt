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
    val comicsLiveData = MutableLiveData<List<MarvelComic>>()
    val seriesLiveData = MutableLiveData<List<MarvelSeries>>()

    override fun onCreate() {
        super.onCreate()
        getCharacter()
        getComics()
        getSeries()
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
        compositeDisposable.add(interactor.getCharacterComics()
            .observeOn(mainScheduler)
            .subscribeOn(ioScheduler)
            .subscribe({
                comicsLiveData.value = it
            }, {
                it.printStackTrace()
            }))
    }

    private fun getSeries(){
        compositeDisposable.add(interactor.getCharacterSeries()
            .observeOn(mainScheduler)
            .subscribeOn(ioScheduler)
            .subscribe({
                seriesLiveData.value = it
            }, {
                it.printStackTrace()
            }))
    }

}