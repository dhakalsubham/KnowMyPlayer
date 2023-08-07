package com.example.knowmyplayer.remote

import com.example.knowmyplayer.model.PlayerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/3/23.
//   Copyright © 2023. All rights reserved.
 */
interface PlayerApi {
    @GET("searchplayers.php")
    suspend fun getPlayerStatsByName(@Query("p") name: String): Response<PlayerResponse>
}