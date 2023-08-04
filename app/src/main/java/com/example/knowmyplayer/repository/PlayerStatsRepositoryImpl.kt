package com.example.knowmyplayer.repository

import com.example.knowmyplayer.model.PlayerResponse
import com.example.knowmyplayer.remote.PlayerApi

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/3/23.
//   Copyright © 2023. All rights reserved.
 */
class PlayerStatsRepositoryImpl(
    private val playerApi: PlayerApi
) : PlayerStatsRepository {
    override suspend fun getPlayerStatsByName(name: String): PlayerResponse {
        return playerApi.getPlayerStatsByName(name)
    }
}