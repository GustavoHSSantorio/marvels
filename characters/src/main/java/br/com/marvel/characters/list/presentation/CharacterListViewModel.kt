package br.com.marvel.characters.list.presentation

import androidx.lifecycle.MutableLiveData
import br.com.marvel.base.BaseViewModel
import br.com.marvel.characters.list.domain.CharacterListInteractor
import br.com.marvel.network.IOScheduler
import br.com.marvel.network.MainScheduler
import br.com.marvel.network.model.MarvelCharacter
import io.reactivex.Scheduler
import javax.inject.Inject

enum class CharacterListState {
    ShowSuccessView,
    ShowErrorView,
    ShowLoading,
    RemoveLoading
}

class CharacterListViewModel @Inject constructor(
    private val interactor: CharacterListInteractor,
    @MainScheduler private val mainScheduler: Scheduler,
    @IOScheduler private val ioScheduler: Scheduler
) : BaseViewModel() {

    val charactersLiveData = MutableLiveData<MutableList<MarvelCharacter>>().apply { value = mutableListOf() }
    val stateLiveData = MutableLiveData<CharacterListState>()

    private var offset = 0

    override fun onCreate() {
        super.onCreate()
        getCharacters()
    }

    fun retry() {
        getCharacters()
    }

    fun onLastItemVisible(lastItemVisible: Int) {
        charactersLiveData.value?.size
            ?.takeIf {lastItemVisible == it - 1}
            ?.run {
                offset += DEFAULT_LIMIT
                getCharacters()
            }
    }

    private fun getCharacters() {
        compositeDisposable.add(
            interactor.getCharacters(limit= DEFAULT_LIMIT, offset= offset)
                .observeOn(mainScheduler)
                .subscribeOn(ioScheduler)
                .doOnSubscribe {
                    if(charactersLiveData.value.isNullOrEmpty())
                        stateLiveData.value = CharacterListState.ShowLoading
                }
                .doFinally {
                    stateLiveData.value = CharacterListState.RemoveLoading
                }
                .doOnSuccess {
                    stateLiveData.value = CharacterListState.ShowSuccessView
                }
                .subscribe({
                    charactersLiveData.value = charactersLiveData.value!!.apply {
                        addAll(it)
                    }
                }, {
                    it.printStackTrace()
                    stateLiveData.value = CharacterListState.ShowErrorView
                })
        )
    }

    companion object{
        private const val DEFAULT_LIMIT = 20
    }
}
