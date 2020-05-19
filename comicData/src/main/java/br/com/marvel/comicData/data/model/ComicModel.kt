package br.com.marvel.comicData.data.model

import br.com.marvel.network.model.*


data class MarvelComicDataWrapper(
    val data: MarvelComicDataContainer? = null
) : BaseModelMarvelWrapper()

data class MarvelComicDataContainer(val results: ArrayList<MarvelComic>? = null) : BaseModelMarvelContainer()

data class MarvelSeriesContainer(
    val results: ArrayList<MarvelSeries>? = null
) : BaseModelMarvelContainer()

data class MarvelSeriesDataWrapper(
    val data: MarvelSeriesContainer? = null
) : BaseModelMarvelWrapper()

data class MarvelStoryDataContainer(val results: ArrayList<MarvelStory>? = null) : BaseModelMarvelContainer()

data class MarvelStoryDataWrapper(
    val data: MarvelStoryDataContainer? = null
) : BaseModelMarvelWrapper()
