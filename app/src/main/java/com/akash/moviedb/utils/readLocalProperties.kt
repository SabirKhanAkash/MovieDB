package com.akash.moviedb.utils

import java.io.FileInputStream
import java.util.Properties

fun readLocalProperties(key: String): String? {
    val properties = Properties()
    val localPropertiesFile = FileInputStream("local.properties")
    properties.load(localPropertiesFile)
    return properties.getProperty(key)
}