package com.simtop.openmarvel.domain.usecases

import com.simtop.openmarvel.core.BaseUseCase
import com.simtop.openmarvel.core.Either
import com.simtop.openmarvel.domain.models.MarvelCharacters
import com.simtop.openmarvel.domain.repository.MarvelRepository
import javax.inject.Inject

class GetMarvelCharactersUseCase @Inject constructor(private val marvelRepository: MarvelRepository) :
    BaseUseCase<MarvelCharacters, GetMarvelCharactersUseCase.Params>() {

    inner class Params(val offset: Int = 0, val limit: Int = 100)

    override suspend fun buildUseCase(params: Params): Either<Exception, MarvelCharacters> {
        val response = try {
            marvelRepository.getMarvelCharacters(offset = params.offset, limit = params.limit)
        } catch (exception: Exception) {
            return Either.Left(exception)
        }
        return Either.Right(response)
    }
}