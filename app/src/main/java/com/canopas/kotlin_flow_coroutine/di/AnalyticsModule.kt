package com.canopas.kotlin_flow_coroutine.di

import com.canopas.kotlin_flow_coroutine.rest.GithubService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setDateFormat(DateFormat.FULL, DateFormat.FULL)
            .create()

        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideGithubService(
        gsonConverterFactory: GsonConverterFactory
    ): GithubService {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(GithubService::class.java)
    }


}