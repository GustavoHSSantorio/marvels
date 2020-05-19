package br.com.marvel.characters.list.domain

import br.com.marvel.characters.list.data.CharacterListRepository
import javax.inject.Inject

interface CharacterListInteractor {
}

class CharacterListInteractorImp @Inject constructor(private val characterListRepository: CharacterListRepository) : CharacterListInteractor {

}