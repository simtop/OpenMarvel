package com.simtop.openmarvel.data.models

import com.google.gson.annotations.SerializedName

data class MarvelCharactersDataResponse(
    @SerializedName("results")
    val marvelHeroResponses: List<MarvelHeroResponse>,
    val offset: Int,
    val total: Int,
    val limit: Int,
    val count: Int
)