package com.simtop.openmarvel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarvelBaseUrlModule {

    const val BASE_URL_NAME = "baseUrl"
    private const val MARVEL_BASE_URL = "https://gateway.marvel.com"

    @Provides
    @Singleton
    @Named(BASE_URL_NAME)
    fun provideBaseUrl(): String {
        return MARVEL_BASE_URL
    }
}