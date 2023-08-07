package com.example.knowmyplayer.feature.dashboard

import com.example.knowmyplayer.model.Player

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/4/23.
//   Copyright © 2023. All rights reserved.
 */
data class PlayerStatsStateHolder(
    val isLoading: Boolean = false,
    val data: MutableList<Player>? = null,
    val error: String = ""
)
