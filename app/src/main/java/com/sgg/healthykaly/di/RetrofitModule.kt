package com.sgg.healthykaly.di

import com.sgg.healthykaly.BuildConfig
import com.sgg.healthykaly.service.RecipeService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = BuildConfig.BASE_URL

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val moshiConverter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BASIC)

    private val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshiConverter))
            .baseUrl(BASE_URL)
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideRecipeService(): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }
}