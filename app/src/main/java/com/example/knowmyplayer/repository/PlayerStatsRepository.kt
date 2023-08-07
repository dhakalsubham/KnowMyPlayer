package com.example.knowmyplayer.repository

import com.example.knowmyplayer.model.PlayerResponse
import com.example.knowmyplayer.remote.NetworkUtils
import com.example.knowmyplayer.remote.PlayerApi

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/3/23.
//   Copyright © 2023. All rights reserved.
 */
class PlayerStatsRepository(
    private val playerApi: PlayerApi
) : BaseRepo() {
    suspend fun getPlayerStatsByName(name: String): NetworkUtils<PlayerResponse> {
        return safeApiCall { playerApi.getPlayerStatsByName(name) }
    }
}