package com.example.knowmyplayer.common

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/4/23.
//   Copyright © 2023. All rights reserved.
 */
sealed class UIEvent<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>() : UIEvent<T>()

    class Success<T>(data: T) : UIEvent<T>(data = data)

    class Error<T>(message: String) : UIEvent<T>(message = message)

}
