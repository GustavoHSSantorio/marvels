package br.com.marvel.characters.list.presentation

import androidx.lifecycle.MutableLiveData
import br.com.marvel.base.BaseViewModel
import br.com.marvel.characters.list.domain.CharacterListInteractor
import br.com.marvel.network.IOScheduler
import br.com.marvel.network.MainScheduler
import br.com.marvel.network.model.MarvelCharacter
import io.reactivex.Scheduler
import javax.inject.Inject

enum class CharacterListState{
    ShowSuccessView,
    ShowErrorView,
    ShowLoading,
    RemoveLoading
}

class CharacterListViewModel @Inject constructor(private val interactor: CharacterListInteractor,
                                                 @MainScheduler private val mainScheduler: Scheduler,
                                                 @IOScheduler private val ioScheduler: Scheduler
) : BaseViewModel() {

    val charactersLiveData = MutableLiveData<List<MarvelCharacter>>()
    val stateLiveData = MutableLiveData<CharacterListState>()

    override fun onCreate() {
        super.onCreate()
        getCharacters()
    }

    fun retry(){
        getCharacters()
    }

    private fun getCharacters(){
        compositeDisposable.add(
            interactor.getCharacters()
                .observeOn(mainScheduler)
                .subscribeOn(ioScheduler)
                .doOnSubscribe {
                    stateLiveData.value = CharacterListState.ShowLoading
                }
                .doFinally {
                    stateLiveData.value = CharacterListState.RemoveLoading
                }
                .doOnSuccess {
                    stateLiveData.value = CharacterListState.ShowSuccessView
                }
                .subscribe({
                    charactersLiveData.value = it
                },{
                    it.printStackTrace()
                    stateLiveData.value = CharacterListState.ShowErrorView
                })
        )
    }
}
