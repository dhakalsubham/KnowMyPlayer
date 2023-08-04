package com.example.knowmyplayer.repository

import com.example.knowmyplayer.model.PlayerResponse

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/3/23.
//   Copyright © 2023. All rights reserved.
 */
interface PlayerStatsRepository {
    suspend fun getPlayerStatsByName(name: String): PlayerResponse
}