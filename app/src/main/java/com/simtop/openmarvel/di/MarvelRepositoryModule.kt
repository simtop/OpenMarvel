package com.simtop.openmarvel.di

import com.simtop.openmarvel.data.repository.MarvelRepositoryImpl
import com.simtop.openmarvel.domain.repository.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MarvelRepositoryModule {
    @Binds
    abstract fun geMarvelRepository(repository: MarvelRepositoryImpl): MarvelRepository
}