package com.simtop.openmarvel.core

abstract class BaseUseCase<T, PARAMS> protected constructor() {

    protected abstract suspend fun buildUseCase(params: PARAMS): Either<Exception, T>

    suspend fun invoke(params: PARAMS) = buildUseCase(params)
}