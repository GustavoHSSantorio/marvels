package br.com.marvel.characterData.data.model

import br.com.marvel.network.model.*

data class MarvelCharacterDataContainer(
    var results: ArrayList<MarvelCharacter>? = null
) : BaseModelMarvelContainer()

data class MarvelCharacterDataWrapper(var data: MarvelCharacterDataContainer? = null) : BaseModelMarvelWrapper()



