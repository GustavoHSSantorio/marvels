package br.com.marvel.network.model

open class BaseModelMarvel {
    var id: Int? = null
    var name: String? = null
    var description: String? = null
    var thumbnail: MarvelImage? = null
    var title: String? = null
}

open class BaseModelMarvelContainer {
    val limit: Int? = null
    val count: Int? = null
    val total: Int? = null
}

open class BaseModelMarvelList {

    val available: Int? = null

    val returned: Int? = null

    val collectionURI: String? = null
}

open class BaseModelMarvelSummary {

    val resourceURI: String? = null

    val name: String? = null
}

open class BaseModelMarvelWrapper {

    val code: Int? = null

    val status: String? = null
}

data class MarvelImage(

    val path: String? = null,
    val extension: String? = null,
    val fullUri : String? = "${path}.${extension}"

)

data class MarvelUrl(
    val type: String? = null,
    val url: String? = null
)

data class MarvelStoryList(
    val items: List<MarvelStorySummary>? = null
) : BaseModelMarvelList()

data class MarvelStorySummary(
    val type: String? = null
)

data class MarvelTextObjects(
    val type: String? = null,

    val text: String? = null,

    val language: String? = null
)

class MarvelComicSummary : BaseModelMarvelSummary()

class MarvelSeriesSummary : BaseModelMarvelSummary()

data class MarvelComicList(
    val items: List<MarvelComicSummary>? = null
) : BaseModelMarvelList()

data class MarvelSeriesList(
    val items: List<MarvelSeriesSummary>? = null
) : BaseModelMarvelList()

data class MarvelSeries(
    val endYear: Int? = null,

    val startYear: Int? = null
) : BaseModelMarvel()

data class MarvelCharacterList(
    val items: List<MarvelCharacterSummary>? = null
) : BaseModelMarvelList()

data class MarvelComicPrice(
    val type: String? = null,
    val price: Float? = null
)

class MarvelCharacterSummary : BaseModelMarvelSummary()

data class MarvelStory(
    val type: String? = null,

    val resourceURI: String? = null
) : BaseModelMarvel()

data class MarvelComic(
    val pageCount: Int? = null,
    val urls: List<MarvelUrl>? = null,
    val collections: List<MarvelComicSummary>? = null,
    val images: List<MarvelImage>? = null,
    val characters: MarvelCharacterList? = null,
    val stories: MarvelStoryList? = null,
    val prices: List<MarvelComicPrice>? = null,
    val textObjects: List<MarvelTextObjects>? = null,
    var charactersList: List<MarvelCharacter>? = null,
    var storiesList: List<MarvelStory>? = null
) : BaseModelMarvel()

class MarvelCharacter : BaseModelMarvel() {
    val urls: List<MarvelUrl>? = null
    var comics: MarvelComicList? = null
    val stories: MarvelStoryList? = null
    val series: MarvelSeriesList? = null
    var comicList: List<MarvelComic>? = null
    var seriesList: List<MarvelSeries>? = null
}
