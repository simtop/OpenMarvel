package com.simtop.openmarvel.domain.usecases

import com.simtop.openmarvel.core.BaseUseCase
import com.simtop.openmarvel.core.Either
import com.simtop.openmarvel.domain.models.MarvelHero
import com.simtop.openmarvel.domain.repository.MarvelRepository
import javax.inject.Inject

class GetMarvelCharacterDetailUseCase @Inject constructor(private val marvelRepository: MarvelRepository) :
    BaseUseCase<MarvelHero, GetMarvelCharacterDetailUseCase.Params>() {

    inner class Params(val characterId: Int)

    override suspend fun buildUseCase(params: Params): Either<Exception, MarvelHero> {
        val response = try {
            marvelRepository.getMarvelCharacterDetail(characterId = params.characterId)
        } catch (exception: Exception) {
            return Either.Left(exception)
        }
        return Either.Right(response)
    }
}