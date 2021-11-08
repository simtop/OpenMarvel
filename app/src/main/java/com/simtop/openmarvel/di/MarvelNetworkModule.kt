package com.simtop.openmarvel.di

import com.simtop.openmarvel.data.network.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarvelNetworkModule {

    @Provides
    @Singleton
    fun provideBitCoinApi(retrofit: Retrofit): MarvelService =
        retrofit.create(MarvelService::class.java)
}