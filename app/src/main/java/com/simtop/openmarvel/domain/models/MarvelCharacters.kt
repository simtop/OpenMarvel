package com.simtop.openmarvel.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelCharacters(
    val marvelHeroResponses: List<MarvelHero>,
    val offset: Int,
    val total: Int,
    val limit: Int,
    val count: Int
): Parcelable