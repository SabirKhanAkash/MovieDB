package com.akash.moviedb.utils

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun isFavorite(applicationContext: Context?, id: Int): Boolean {
    var answer = false
    GlobalScope.launch(Dispatchers.IO) {
        val show = getShowFromRoomDB(applicationContext!!)
        for (i in show.indices) {
            if (id == show[i].id) {
                answer = true
                break
            }
        }
        answer = false
    }
    return answer
}