package com.simtop.openmarvel.domain.repository

import com.simtop.openmarvel.domain.models.MarvelCharacters

interface MarvelRepository {

    suspend fun getMarvelCharacters(offset: Int = 0, limit: Int = 100): MarvelCharacters
}