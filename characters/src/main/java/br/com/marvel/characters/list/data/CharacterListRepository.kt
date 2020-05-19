package br.com.marvel.characters.list.data

import javax.inject.Inject

interface CharacterListRepository {
}

class CharacterListRepositoryImp @Inject constructor(private val service : CharacterListService) : CharacterListRepository{

}
