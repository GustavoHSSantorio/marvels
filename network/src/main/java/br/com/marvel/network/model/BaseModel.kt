package br.com.marvel.network.model

open class BaseModelMarvel {
    var id: Int? = null
    var name: String? = null
    var description: String? = null
    var thumbMail: MarvelImage? = null
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

    val extension: String? = null
)

data class MarvelUrl(
    val type: String? = null,
    val url: String? = null
)

data class MarvelStoryList(
    val items: ArrayList<MarvelStorySummary>? = null
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
    val items: ArrayList<MarvelComicSummary>? = null
) : BaseModelMarvelList()

data class MarvelSeriesList(
    val items: ArrayList<MarvelSeriesSummary>? = null
) : BaseModelMarvelList()

data class MarvelSeries(
    val endYear: Int? = null,

    val startYear: Int? = null
) : BaseModelMarvel()

data class MarvelCharacterList(
    val items: ArrayList<MarvelCharacterSummary>? = null
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
    val urls: ArrayList<MarvelUrl>? = null,
    val collections: ArrayList<MarvelComicSummary>? = null,
    val images: ArrayList<MarvelImage>? = null,
    val characters: MarvelCharacterList? = null,
    val stories: MarvelStoryList? = null,
    val prices: ArrayList<MarvelComicPrice>? = null,
    val textObjects: ArrayList<MarvelTextObjects>? = null,
    var charactersList: ArrayList<MarvelCharacter>? = null,
    var storiesList: ArrayList<MarvelStory>? = null
) : BaseModelMarvel()

class MarvelCharacter : BaseModelMarvel {

    constructor() : super()
    constructor(id: Int) : super() {
        this.id = id
    }

    val urls: ArrayList<MarvelUrl>? = null
    var comics: MarvelComicList? = null
    val stories: MarvelStoryList? = null
    val series: MarvelSeriesList? = null
    var comicList: ArrayList<MarvelComic>? = null
    var seriesList: ArrayList<MarvelSeries>? = null
}
