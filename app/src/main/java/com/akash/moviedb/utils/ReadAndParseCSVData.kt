package com.akash.moviedb.utils

import android.content.Context
import com.akash.moviedb.R
import com.akash.moviedb.model.CSVData
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun readAndParseCSVData(context: Context): List<CSVData> {
    val data = mutableListOf<CSVData>()

    try {
        val inputStream = context.resources.openRawResource(R.raw.stock)
        val reader = BufferedReader(InputStreamReader(inputStream))

        reader.readLine()

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            val parts = line?.split(",") ?: continue
            if (parts.size >= 6) {
                val timestamp = parts[0]
                val close = parts[4].toFloat()
                val csvData = CSVData(timestamp, close)
                data.add(csvData)
            }
        }

        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return data
}

