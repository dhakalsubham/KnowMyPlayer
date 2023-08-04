package com.example.knowmyplayer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowmyplayer.model.Player
import com.example.knowmyplayer.model.PlayerResponse
import com.example.knowmyplayer.repository.PlayerStatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/3/23.
//   Copyright © 2023. All rights reserved.
 */
@HiltViewModel
class MainActivityViewModel
@Inject constructor(private val repository: PlayerStatsRepository) : ViewModel() {

    val playerStats = MutableLiveData<Player?>()

    init {
        viewModelScope.launch {
            try {
                getPlayerStatsByName("Messi")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    suspend fun getPlayerStatsByName(name: String) {
        var response = repository.getPlayerStatsByName(name)
    }
}