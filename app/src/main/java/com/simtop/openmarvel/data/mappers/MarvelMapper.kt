package com.simtop.openmarvel.data.mappers

import com.simtop.openmarvel.data.models.MarvelCharactersDataResponse
import com.simtop.openmarvel.data.models.MarvelCharactersResponse
import com.simtop.openmarvel.data.models.MarvelHeroResponse
import com.simtop.openmarvel.data.models.MarvelThumbnailResponse
import com.simtop.openmarvel.domain.models.MarvelCharacters
import com.simtop.openmarvel.domain.models.MarvelCharactersApiDomain
import com.simtop.openmarvel.domain.models.MarvelHero

object MarvelMapper {
    fun fromMarvelHeroResponseToMarvelHero(marvelHeroResponse: MarvelHeroResponse): MarvelHero =
        with(marvelHeroResponse) {
            MarvelHero(
                id = id,
                name = name,
                description = description,
                marvelThumbnailUrl = fromMarvelThumbnailResponseToMarvelThumbnailUrl(
                    marvelThumbnailResponse
                )
            )
        }

    private fun fromMarvelThumbnailResponseToMarvelThumbnailUrl(marvelThumbnailResponse: MarvelThumbnailResponse) =
        with(marvelThumbnailResponse) {
            "$path.$extension"
        }

    fun fromMarvelCharactersDataResponseToMarvelCharacters(marvelCharactersDataResponse: MarvelCharactersDataResponse): MarvelCharacters =
        with(marvelCharactersDataResponse) {
            MarvelCharacters(
                marvelHeroResponses = marvelHeroResponses.map { marvelHeroResponse ->
                    fromMarvelHeroResponseToMarvelHero(marvelHeroResponse)
                },
                offset = offset,
                limit = limit,
                count = count, total = total
            )
        }

    fun fromMarvelCharactersResponseToMarvelCharactersApiDomain(marvelCharactersResponse: MarvelCharactersResponse): MarvelCharactersApiDomain =
        with(marvelCharactersResponse) {
            MarvelCharactersApiDomain(
                code = code,
                status = status,
                marvelCharacters = fromMarvelCharactersDataResponseToMarvelCharacters(
                    marvelCharactersDataResponse
                )
            )
        }
}