package br.com.marvel.characterData.data

import br.com.marvel.characterData.data.model.MarvelCharacterDataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface CharacterService {
    @GET("v1/public/characters")
    fun getCharacters(@Query("limit") limit: Int, @Query("offset") offset: Int): Single<MarvelCharacterDataWrapper>

    @GET("v1/public/characters")
    fun getCharactersBeginLetter(@Query("limit") limit: Int, @Query("nameStartsWith") letter: String): Single<MarvelCharacterDataWrapper>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacter(@Path("characterId") characterId: Int?): Single<MarvelCharacterDataWrapper>

    @GET
    fun getGenericCharacter(@Url url: String): Single<MarvelCharacterDataWrapper>
}
