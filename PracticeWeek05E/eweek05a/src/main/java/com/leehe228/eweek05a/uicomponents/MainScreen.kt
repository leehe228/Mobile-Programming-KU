package com.leehe228.eweek05a.uicomponents

import android.content.res.Configuration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leehe228.eweek05a.R
import com.leehe228.eweek05a.model.ButtonType
import com.leehe228.eweek05a.model.ImageData
import com.leehe228.eweek05a.model.ImageUri
import com.leehe228.eweek05a.viewmodel.ImageViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    imageViewModel: ImageViewModel = viewModel()
) {
    // val imageViewModel: ImageViewModel = viewModel() // 인라인 선언 가능
    val imageList = imageViewModel.imageList
    var scrollState = rememberScrollState()
    val orientation = LocalConfiguration.current.orientation

    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageList(imageList = imageList)
        }
    } else {
        Row(
            modifier = Modifier.fillMaxHeight().horizontalScroll(scrollState),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageList(imageList = imageList)
        }
    }

    /* var img1State by rememberSaveable(stateSaver = ImageData.ImageSaver) {
        mutableStateOf(
            ImageData(
                image = ImageUri.ResImage(R.drawable.img1),
                buttonType = ButtonType.BADGE,
                likes = 50
            )
        )
    }

    var img2State by rememberSaveable(stateSaver = ImageData.ImageSaver) {
        mutableStateOf(
            ImageData(
                image = ImageUri.ResImage(R.drawable.img2),
                buttonType = ButtonType.EMOJI,
                likes = 100,
                dislikes = 10
            )
        )
    }

    Column {
        ImageWithButton(image = img1State.image) {
            ButtonWithBadge(likes = img1State.likes) {
                // img1State.likes++ 객체 내 일부 속성이 바뀐다고 해서 recomposition이 일어나지 않음
                img1State = img1State.copy(likes = img1State.likes + 1) // img1State immutable
            }
        }

        ImageWithButton(image = img2State.image) {
            ButtonWithEmoji(
                likes = img2State.likes,
                dislikes = img2State.dislikes,
                onClickLikes = {
                    img2State = img2State.copy(likes = img2State.likes + 1)
                },
                onClickDisLikes = {
                    img2State = img2State.copy(dislikes = img2State.dislikes + 1)
                }
            )
        }
    } */
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
