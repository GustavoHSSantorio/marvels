package br.com.marvels.comicData

import br.com.marvel.comicData.data.model.MarvelComicDataContainer
import br.com.marvel.comicData.data.model.MarvelComicDataWrapper
import br.com.marvel.comicData.data.model.MarvelSeriesContainer
import br.com.marvel.comicData.data.model.MarvelSeriesDataWrapper
import br.com.marvel.network.model.MarvelComic
import br.com.marvel.network.model.MarvelSeries


internal const val characterId  = 1
internal const val name  = "name"
internal const val limit  = 1
internal const val offset  = 1

internal val comicMarvel = MarvelComic()
internal val comicList  = listOf(comicMarvel)
internal val comicDataContainer  = MarvelComicDataContainer(comicList)
internal val comicDataMarvelWrapper  = MarvelComicDataWrapper(comicDataContainer)

internal val seriesMarvel = MarvelSeries()
internal val seriesList  = listOf(seriesMarvel)
internal val seriesDataContainer  = MarvelSeriesContainer(seriesList)
internal val seriesDataMarvelWrapper  = MarvelSeriesDataWrapper(seriesDataContainer)