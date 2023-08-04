package com.example.knowmyplayer.domain.use_cases

import com.example.knowmyplayer.common.UIEvent
import com.example.knowmyplayer.repository.PlayerStatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/4/23.
//   Copyright © 2023. All rights reserved.
 */
class GetPlayerUseCase(private val playerStatsRepository: PlayerStatsRepository) {
    operator fun invoke() = flow {
        emit(UIEvent.Loading())
        emit(UIEvent.Success(playerStatsRepository.getPlayerStatsByName("")))
    }.catch {
        emit(UIEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}