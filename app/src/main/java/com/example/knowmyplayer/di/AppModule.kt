package com.example.knowmyplayer.di

import com.example.knowmyplayer.constants.AppConstants
import com.example.knowmyplayer.remote.PlayerApi
import com.example.knowmyplayer.repository.PlayerStatsRepository
import com.example.knowmyplayer.repository.PlayerStatsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/3/23.
//   Copyright © 2023. All rights reserved.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesPlayerApi(): PlayerApi {
        return Retrofit.Builder()
                .baseUrl(AppConstants.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PlayerApi::class.java)
    }

    @Provides
    @Singleton
    fun providesPlayerRepository(playerApi: PlayerApi): PlayerStatsRepository {
        return PlayerStatsRepositoryImpl(playerApi)
    }
}