package com.leehe228.eweek05a.model

import androidx.compose.runtime.mutableStateListOf
import com.leehe228.eweek05a.R

object ImageListFactory {
    fun makeListImage() = mutableStateListOf(
        ImageData(
            image = ImageUri.ResImage(R.drawable.img1),
            buttonType = ButtonType.BADGE,
            likes = 50
        ),
        ImageData(
            image = ImageUri.ResImage(R.drawable.img2),
            buttonType = ButtonType.EMOJI,
            likes = 100,
            dislikes = 10
        ),
        ImageData(
            image = ImageUri.ResImage(R.drawable.img3),
            buttonType = ButtonType.ICON,
            likes = 30
        ),
        ImageData(
            image = ImageUri.ResImage(R.drawable.img1),
            buttonType = ButtonType.BADGE,
            likes = 50
        ),
        ImageData(
            image = ImageUri.ResImage(R.drawable.img2),
            buttonType = ButtonType.EMOJI,
            likes = 100,
            dislikes = 10
        ),
        ImageData(
            image = ImageUri.ResImage(R.drawable.img3),
            buttonType = ButtonType.ICON,
            likes = 30
        )
    )
}