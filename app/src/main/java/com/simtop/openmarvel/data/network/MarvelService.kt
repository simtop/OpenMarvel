package com.simtop.openmarvel.data.network

import com.simtop.openmarvel.data.models.MarvelCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val TIME_STAMP = "ts"
private const val API_KEY = "apikey"
private const val HASH = "hash"
private const val OFFSET = "offset"
private const val LIMIT = "limit"
private const val GET_CHARACTERS_ENDPOINT = "characters"
private const val MARVEL_PATH = "/v1/public/"
private const val GET_CHARACTER_DETAIL_PATH = "characterId"
private const val GET_CHARACTER_DETAIL_COMPLETE_ENDPOINT = "/{$GET_CHARACTER_DETAIL_PATH}"


interface MarvelService {
    @GET(MARVEL_PATH + GET_CHARACTERS_ENDPOINT)
    suspend fun getMarvelCharacters(
        @Query(TIME_STAMP) timeStamp: String,
        @Query(API_KEY) apiKey: String,
        @Query(HASH) hash: String,
        @Query(OFFSET) offset: Int,
        @Query(LIMIT) limit: Int
    ): MarvelCharactersResponse

    @GET(MARVEL_PATH + GET_CHARACTERS_ENDPOINT + GET_CHARACTER_DETAIL_COMPLETE_ENDPOINT)
    suspend fun getMarvelCharacterDetail(
        @Path(GET_CHARACTER_DETAIL_PATH) characterId: Int,
        @Query(TIME_STAMP) timeStamp: String,
        @Query(API_KEY) apiKey: String,
        @Query(HASH) hash: String
    ): MarvelCharactersResponse
}