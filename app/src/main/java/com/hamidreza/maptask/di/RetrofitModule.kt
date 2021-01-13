package com.hamidreza.maptask.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hamidreza.maptask.data.remote.MapApi
import com.hamidreza.maptask.utils.Conts.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RetrofitModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit.Builder): MapApi {
        return retrofit.build().create(MapApi::class.java)
    }
}