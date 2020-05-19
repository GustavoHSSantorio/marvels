package br.com.marvel.characters.list.domain

import br.com.marvel.characterData.data.CharacterRepository
import javax.inject.Inject

interface CharacterListInteractor

class CharacterListInteractorImp @Inject constructor(private val repository: CharacterRepository) : CharacterListInteractor
