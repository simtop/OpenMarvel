package com.simtop.openmarvel.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelHero(
    val id: String,
    val name: String,
    val description: String,
    val marvelThumbnailUrl: String
): Parcelable