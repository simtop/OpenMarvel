package com.simtop.openmarvel.domain.repository

import com.simtop.openmarvel.domain.models.MarvelCharacters
import com.simtop.openmarvel.domain.models.MarvelHero

interface MarvelRepository {

    suspend fun getMarvelCharacters(offset: Int = 0, limit: Int = 100): MarvelCharacters

    suspend fun getMarvelCharacterDetail(characterId: Int): MarvelHero
}