package com.simtop.openmarvel.data.repository

import com.simtop.openmarvel.data.mappers.MarvelMapper
import com.simtop.openmarvel.data.remotesources.MarvelRemoteSource
import com.simtop.openmarvel.domain.models.MarvelCharacters
import com.simtop.openmarvel.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val marvelRemoteSource: MarvelRemoteSource
) : MarvelRepository {

    //TODO: I leave the control of authorization errors for the future.
    // But in the future it would be nice to return MarvelCharactersApiDomain
    override suspend fun getMarvelCharacters(offset: Int, limit: Int): MarvelCharacters =
        MarvelMapper.fromMarvelCharactersResponseToMarvelCharactersApiDomain(
            marvelRemoteSource.getMarvelCharacterList(
                offset = offset,
                limit = limit
            )
        ).marvelCharacters
}