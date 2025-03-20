package com.massir.listsort.inject

import com.massir.listsort.api.FetchHiringService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    @Singleton
    fun provideFetchHiringService(@FetchHiringRetrofit retrofit: Retrofit): FetchHiringService = retrofit.create(FetchHiringService::class.java)
}
