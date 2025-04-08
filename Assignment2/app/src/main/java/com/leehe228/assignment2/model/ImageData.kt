package com.leehe228.assignment2.model

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import kotlin.jvm.java

data class ImageData(
    val imageID: Int,
    val imageName: String,
    val imageShowing: Boolean = true
)
/* {
    companion object {
        val imageIDKey = "imageID"
        val imageNameKey = "imageName"
        val imageShowingKey = "imageShowing"

        val imageDataMapSaver = mapSaver(
            save = {
                mapOf(
                    imageIDKey to it.imageID,
                    imageNameKey to it.imageName,
                    imageShowingKey to it.imageShowing
                )
            },
            restore = {
                ImageData(
                    imageID = it[imageIDKey] as Int,
                    imageName = it[imageNameKey] as String,
                    imageShowing = it[imageShowingKey] as Boolean
                )
            }
        )

        val imageDataListSaver = listSaver<ImageData, Any>(
            save = {
                listOf(
                    it.imageID,
                    it.imageName,
                    it.imageShowing
                )
            },
            restore = {
                ImageData(
                    imageID = it[0] as Int,
                    imageName = it[1] as String,
                    imageShowing = it[2] as Boolean
                )
            }
        )

        val imageDataSaver = Saver<ImageData, Any>(
            save = {
                listOf(
                    it.imageID,
                    it.imageName,
                    it.imageShowing
                )
            },
            restore = {
                val list = it as List<Any>
                ImageData(
                    imageID = list[0] as Int,
                    imageName = list[1] as String,
                    imageShowing = list[2] as Boolean
                )
            }
        )
    }
} */
