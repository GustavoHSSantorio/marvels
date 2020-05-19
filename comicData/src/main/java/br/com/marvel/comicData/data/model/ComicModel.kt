package br.com.marvel.comicData.data.model

import br.com.marvel.network.model.BaseModelMarvelContainer
import br.com.marvel.network.model.BaseModelMarvelWrapper
import br.com.marvel.network.model.MarvelComic
import br.com.marvel.network.model.MarvelSeries
import br.com.marvel.network.model.MarvelStory

data class MarvelComicDataWrapper(
    val data: MarvelComicDataContainer? = null
) : BaseModelMarvelWrapper()

data class MarvelComicDataContainer(val results: List<MarvelComic>? = null) : BaseModelMarvelContainer()

data class MarvelSeriesContainer(
    val results: List<MarvelSeries>? = null
) : BaseModelMarvelContainer()

data class MarvelSeriesDataWrapper(
    val data: MarvelSeriesContainer? = null
) : BaseModelMarvelWrapper()

data class MarvelStoryDataContainer(val results: List<MarvelStory>? = null) : BaseModelMarvelContainer()

data class MarvelStoryDataWrapper(
    val data: MarvelStoryDataContainer? = null
) : BaseModelMarvelWrapper()
