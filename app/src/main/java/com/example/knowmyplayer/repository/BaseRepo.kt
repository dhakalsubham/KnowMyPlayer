package com.example.knowmyplayer.repository

import com.example.knowmyplayer.remote.NetworkUtils
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/7/23.
//   Copyright © 2023. All rights reserved.
 */
abstract class BaseRepo() {
    suspend fun <T> safeApiCall(api: suspend () -> Response<T>): NetworkUtils<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = api()
                if (response.isSuccessful) {
                    NetworkUtils.Success(data = response.body()!!)
                } else {
                    NetworkUtils.Error(message = "Something went wrong")
                }
            } catch (e: HttpException) {
                NetworkUtils.Error(message = "Something went wrong")
            } catch (e: IOException) {
                NetworkUtils.Error(message = "Please check your network connection")
            } catch (e: Exception) {
                NetworkUtils.Error(message = "Something went wrong")
            }
        }
    }
}