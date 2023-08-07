package com.example.knowmyplayer.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowmyplayer.model.PlayerResponse
import com.example.knowmyplayer.remote.NetworkUtils
import com.example.knowmyplayer.repository.PlayerStatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/3/23.
//   Copyright © 2023. All rights reserved.
 */
@HiltViewModel
class MainActivityViewModel
@Inject constructor(private val repository: PlayerStatsRepository) : ViewModel() {

    private val _playerStats = MutableLiveData<NetworkUtils<PlayerResponse>>()
    val playerStats: LiveData<NetworkUtils<PlayerResponse>> = _playerStats

    suspend fun getPlayerStatsByName(name: String) {
        viewModelScope.launch {
            _playerStats.postValue(NetworkUtils.Loading())
            _playerStats.postValue(repository.getPlayerStatsByName(name))
        }

    }
}