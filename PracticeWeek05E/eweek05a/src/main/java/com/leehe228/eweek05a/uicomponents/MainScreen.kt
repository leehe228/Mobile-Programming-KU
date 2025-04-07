package com.leehe228.eweek05a.uicomponents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.leehe228.eweek05a.R
import com.leehe228.eweek05a.model.ButtonType
import com.leehe228.eweek05a.model.ImageData
import com.leehe228.eweek05a.model.ImageUri

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var img1State by rememberSaveable {
        mutableStateOf(
            ImageData(
                image = ImageUri.ResImage(R.drawable.img1),
                buttonType = ButtonType.BADGE,
                likes = 50
            ),
            ImageData(
                image = ImageUri.ResImage(R.drawable.img2),
                buttonType = ButtonType.ICON,
                likes = 40,
                dislikes = 3
            )
        )
    }
}
