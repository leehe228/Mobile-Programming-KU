package com.leehe228.assignment2.uicomponents

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.leehe228.assignment2.model.ImageData
import com.leehe228.assignment2.R

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val orientation = LocalConfiguration.current.orientation
    val imageDataListSaver = listSaver<SnapshotStateList<ImageData>, Any>(
        save = { list ->
            list.flatMap {
                listOf<Any>(
                    it.imageID,
                    it.imageName,
                    it.imageShowing
                )
            }
        },
        restore = { flat ->
            flat.chunked(3).map { (imageID, imageName, imageShowing) ->
                ImageData(
                    imageID = imageID as Int,
                    imageName = imageName as String,
                    imageShowing = imageShowing as Boolean
                )
            }.toMutableStateList()
        }
    )

    val imageDataList = rememberSaveable(saver = imageDataListSaver) {
        mutableStateListOf<ImageData>(
            ImageData(R.drawable.arms, "arms"),
            ImageData(R.drawable.ears, "ears"),
            ImageData(R.drawable.shoes, "shoes"),
            ImageData(R.drawable.eyes, "eyes"),
            ImageData(R.drawable.eyebrows, "eyebrows"),
            ImageData(R.drawable.mouth, "mouth"),
            ImageData(R.drawable.nose, "nose"),
            ImageData(R.drawable.mustache, "mustache"),
            ImageData(R.drawable.glasses, "glasses"),
            ImageData(R.drawable.hat, "hat")
        )
    }

    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ImageView(imageDataList = imageDataList)
            CheckBoxView(imageDataList = imageDataList)
        }
    } else { // orientation == Configuration.ORIENTATION_LANDSCAPE
        Row(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ImageView(imageDataList = imageDataList)
            CheckBoxView(imageDataList = imageDataList)
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
