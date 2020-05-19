package br.com.marvel.comicData.data

import br.com.marvel.comicData.data.model.MarvelComicDataWrapper
import br.com.marvel.comicData.data.model.MarvelSeriesDataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ComicService {
    @GET("v1/public/comics")
    fun getComics(@Query("limit") limit: Int, @Query("offset") offset: Int): Single<MarvelComicDataWrapper>

    @GET("v1/public/comics")
    fun getComicsBeginLetter(@Query("limit") limit: Int, @Query("titleStartsWith") titleStartsWith: String): Single<MarvelComicDataWrapper>

    @GET("/v1/public/comics/{comicId}")
    fun getComic(@Path("comicId") characterId: Int?): Single<MarvelComicDataWrapper>

    @GET
    fun getGenericComic(@Url url: String): Single<MarvelComicDataWrapper>

    @GET
    fun getGenericSeries(@Url url: String): Single<MarvelSeriesDataWrapper>
}
