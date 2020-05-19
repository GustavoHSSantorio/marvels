package br.com.marvel.characters.list.presentation

import br.com.marvel.base.BaseViewModel
import br.com.marvel.characters.list.domain.CharacterListInteractor
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(private val interactor: CharacterListInteractor) : BaseViewModel(){

    override fun onCreate() {
        super.onCreate()
    }
}