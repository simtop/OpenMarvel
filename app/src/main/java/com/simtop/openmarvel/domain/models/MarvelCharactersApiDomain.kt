package com.simtop.openmarvel.domain.models

/*
We need this model to control the authorization Errors
 */
data class MarvelCharactersApiDomain(
    val code: Int,
    val status: String,
    val marvelCharacters: MarvelCharacters
)