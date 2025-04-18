package com.massir.listsort.inject

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(json: Json): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))

    @FetchHiringRetrofit
    @Provides
    @Singleton
    fun provideRetrofitYelpApi(
        @FetchHiringUrl fetchHiringUrl: String,
        okHttpClient: OkHttpClient,
        retrofitBuilder: Retrofit.Builder,
    ): Retrofit = retrofitBuilder
        .baseUrl(fetchHiringUrl)
        .client(okHttpClient)
        .build()
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class FetchHiringRetrofit
