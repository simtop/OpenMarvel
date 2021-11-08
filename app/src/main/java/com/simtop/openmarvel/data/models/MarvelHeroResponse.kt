package com.simtop.openmarvel.data.models

import com.google.gson.annotations.SerializedName

data class MarvelHeroResponse(
    val id: String,
    val name: String,
    val description: String,
    @SerializedName("thumbnail")
    val marvelThumbnailResponse: MarvelThumbnailResponse
)