package com.example.knowmyplayer.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/6/23.
//   Copyright © 2023. All rights reserved.
 */
object Utils {
    fun closeKeyboard(activity: Activity) {
        var view = activity.currentFocus
        if (view != null) {
            var inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}