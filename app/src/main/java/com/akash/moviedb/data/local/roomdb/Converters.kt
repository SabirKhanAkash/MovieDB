package com.akash.moviedb.data.local.roomdb

import androidx.room.TypeConverter
import com.akash.moviedb.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromInt(value: Int): ArrayList<Int> {
        val list = ArrayList<Int>()
        list.add(value)
        return list
    }

    @TypeConverter
    fun fromIntArrayList(list: ArrayList<Int>): Int {
        return list[0]
    }

    @TypeConverter
    fun fromGenreList(value: ArrayList<Genre>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toGenreList(value: String): ArrayList<Genre> {
        val type = object : TypeToken<ArrayList<Genre>>() {}.type
        return Gson().fromJson(value, type)
    }
}
