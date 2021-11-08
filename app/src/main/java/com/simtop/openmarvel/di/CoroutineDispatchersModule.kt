package com.simtop.openmarvel.di

import com.simtop.openmarvel.core.CoroutineDispatcherProvider
import com.simtop.openmarvel.core.DefaultCoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatchersModule {

    @Provides
    @Singleton
    fun providesDispatcherProvider(): CoroutineDispatcherProvider = DefaultCoroutineDispatcherProvider()

}