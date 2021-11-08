package com.simtop.openmarvel

import com.simtop.openmarvel.data.models.MarvelCharactersDataResponse
import com.simtop.openmarvel.data.models.MarvelCharactersResponse
import com.simtop.openmarvel.data.models.MarvelHeroResponse
import com.simtop.openmarvel.data.models.MarvelThumbnailResponse
import com.simtop.openmarvel.domain.models.MarvelCharacters
import com.simtop.openmarvel.domain.models.MarvelHero

const val FAKE_JSON = "fake_json_response.json"

val fakeThumbnailResponse = MarvelThumbnailResponse(
    path = "image",
    extension = "jpg"
)

val fakeMarvelHeroResponse = MarvelHeroResponse(
    id = "1",
    name = "Sim Man",
    description = "Best",
    marvelThumbnailResponse = fakeThumbnailResponse
)

val fakeMarvelCharactersDataResponse = MarvelCharactersDataResponse(
    marvelHeroResponses = listOf(fakeMarvelHeroResponse),
    offset = 0,
    limit = 1,
    total = 1559,
    count = 1
)

val fakeMarvelApiResponse = MarvelCharactersResponse(
    code = 200,
    status = "Ok",
    marvelCharactersDataResponse = fakeMarvelCharactersDataResponse
)

val fakeMarvelHero = MarvelHero(
    id = "1",
    name = "Sim Man",
    description = "Best",
    marvelThumbnailUrl = "image.jpg"
)

val fakeMarvelCharacters = MarvelCharacters(
    marvelHeroResponses = listOf(fakeMarvelHero),
    offset = 0,
    limit = 1,
    total = 1559,
    count = 1
)

val fakeErrorName = "Error getting list of heroes"

val fakeException = Exception(fakeErrorName)
