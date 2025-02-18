package br.com.marvel.comicData.data

import br.com.marvel.comicData.data.model.MarvelComicDataWrapper
import br.com.marvel.comicData.data.model.MarvelSeriesDataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicService {
    @GET("v1/public/comics")
    fun getComics(@Query("limit") limit: Int, @Query("offset") offset: Int): Single<MarvelComicDataWrapper>

    @GET("v1/public/comics")
    fun getComicsBeginLetter(@Query("limit") limit: Int, @Query("offset") offset: Int, @Query("titleStartsWith") titleStartsWith: String): Single<MarvelComicDataWrapper>

    @GET("/v1/public/comics/{comicId}")
    fun getComic(@Path("comicId") characterId: Int?): Single<MarvelComicDataWrapper>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getCharacterComics(@Path("characterId") characterId: Int?, @Query("limit") limit: Int, @Query("offset") offset: Int): Single<MarvelComicDataWrapper>

    @GET("/v1/public/characters/{characterId}/series")
    fun getCharacterSeries(@Path("characterId") characterId: Int?, @Query("limit") limit: Int, @Query("offset") offset: Int): Single<MarvelSeriesDataWrapper>
}
