package com.example.knowmyplayer.remote

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/4/23.
//   Copyright © 2023. All rights reserved.
 */

import com.example.knowmyplayer.common.NetworkResponse
import retrofit2.Response

fun <T> Result<Response<T>>.getNetworkResponse(): NetworkResponse<T?> {
    onFailure {
        return NetworkResponse.Error(it.message)
    }
    getOrNull().let {
        return if (it != null && it.isSuccessful) {
            NetworkResponse.Success(it.body())
        } else {
            NetworkResponse.Error(it?.errorBody().toString())
        }
    }
}