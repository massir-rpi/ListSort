package com.massir.listsort.inject

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BaseUrlModule {
    private const val BASE_URL_FETCH_HIRING = "https://fetch-hiring.s3.amazonaws.com/"

    @FetchHiringUrl
    @Provides
    @Singleton
    fun provideFetchHiringUrl(): String = BASE_URL_FETCH_HIRING
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class FetchHiringUrl
