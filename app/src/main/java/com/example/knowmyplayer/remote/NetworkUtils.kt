package com.example.knowmyplayer.remote

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/7/23.
//   Copyright © 2023. All rights reserved.
 */
sealed class NetworkUtils<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : NetworkUtils<T>(data = data)

    class Error<T>(message: String) : NetworkUtils<T>(message = message)

    class Loading<T> : NetworkUtils<T>()
}
