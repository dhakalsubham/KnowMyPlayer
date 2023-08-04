package com.example.knowmyplayer.common

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/4/23.
//   Copyright © 2023. All rights reserved.
 */

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(val error: String? = null) : NetworkResponse<Nothing>()
}
