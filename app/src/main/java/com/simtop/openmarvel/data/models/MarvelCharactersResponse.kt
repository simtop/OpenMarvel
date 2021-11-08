package com.simtop.openmarvel.data.models

import com.google.gson.annotations.SerializedName

data class MarvelCharactersResponse(
    val code: Int,
    val status: String,
    @SerializedName("data")
    val marvelCharactersDataResponse: MarvelCharactersDataResponse
)