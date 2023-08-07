package com.example.knowmyplayer.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.knowmyplayer.constants.AppConstants
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/6/23.
//   Copyright © 2023. All rights reserved.
 */

@RequiresApi(Build.VERSION_CODES.O)
fun String.convertDOBToAge(): String {
    val dateFormatter = DateTimeFormatter.ofPattern(AppConstants.dateFormat)
    val dateOfBirth = LocalDate.parse(this, dateFormatter)
    val currentDate = LocalDate.now()
    val age = Period.between(dateOfBirth, currentDate)
    return "${age.years} years"
}