package com.leehe228.assignment2.uicomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.leehe228.assignment2.R
import com.leehe228.assignment2.model.ImageData

@Composable
fun ImageView(
    imageDataList: SnapshotStateList<ImageData>,
    modifier: Modifier = Modifier
) {
    Box() {
        AsyncImage(
            model = R.drawable.body,
            contentDescription = null,
            modifier = modifier
        )

        imageDataList.forEach { imageData ->
            if (imageData.imageShowing) {
                AsyncImage(
                    model = imageData.imageID,
                    contentDescription = null,
                    modifier = modifier
                )
            }
        }
    }
}

@Preview
@Composable
private fun ImageViewPreview() {
    MainScreen()
}
