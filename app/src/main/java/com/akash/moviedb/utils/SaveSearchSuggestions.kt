package com.akash.moviedb.utils

import android.content.Context
import showTopToast

fun saveSearchSuggestionToDisk(
    ctx: Context,
    searchType: String,
    suggestions: MutableList<Any?>
): Boolean {
    return try {
        var sharedPref: SharedPref = SharedPref()
        for (i in 0 until suggestions.size) {
            if (searchType == "movie")
                sharedPref.setString(ctx, ("ms$i").toString(), suggestions[i].toString())
            else if (searchType == "tv")
                sharedPref.setString(ctx, ("ts$i").toString(), suggestions[i].toString())
        }
        true
    } catch (e: Error) {
        showTopToast(ctx, e.toString(), "short", "negative")
        false
    }
}