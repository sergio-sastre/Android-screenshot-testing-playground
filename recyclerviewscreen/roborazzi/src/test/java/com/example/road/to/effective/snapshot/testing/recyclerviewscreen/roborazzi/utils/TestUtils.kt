package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.roborazzi.utils

import java.io.File

fun filePath(name: String): String {
    val path = System.getProperty("user.dir")
    val file = File("$path/src/test", "$name.png")
    return file.path
}
