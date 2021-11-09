package com.simtop.openmarvel.data.remotesources

import com.simtop.openmarvel.BuildConfig
import com.simtop.openmarvel.core.md5
import com.simtop.openmarvel.data.models.MarvelCharactersResponse
import com.simtop.openmarvel.data.network.MarvelService
import javax.inject.Inject

class MarvelRemoteSource @Inject constructor(private val service: MarvelService) {

    private val apiKey = BuildConfig.API_KEY
    private val privateApiKey = BuildConfig.PRIVATE_API_KEY

    suspend fun getMarvelCharacterList(
        offset: Int = 0,
        limit: Int = 100,
        timestamp: String = System.currentTimeMillis().toString(),
        hash: String = (timestamp + privateApiKey + apiKey).md5()
    ): MarvelCharactersResponse {

        return service.getMarvelCharacters(
            timeStamp = timestamp,
            apiKey = apiKey,
            hash = hash,
            offset = offset,
            limit = limit
        )
    }

    suspend fun getMarvelCharacterDetail(
        timestamp: String = System.currentTimeMillis().toString(),
        hash: String = (timestamp + privateApiKey + apiKey).md5(),
        characterId: Int
    ): MarvelCharactersResponse {

        return service.getMarvelCharacterDetail(
            characterId = characterId,
            timeStamp = timestamp,
            apiKey = apiKey,
            hash = hash
        )
    }
}