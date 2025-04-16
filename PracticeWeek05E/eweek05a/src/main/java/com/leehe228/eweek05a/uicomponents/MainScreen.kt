package com.leehe228.eweek05a.uicomponents

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leehe228.eweek05a.R
import com.leehe228.eweek05a.model.ButtonType
import com.leehe228.eweek05a.model.ImageData
import com.leehe228.eweek05a.model.ImageUri
import com.leehe228.eweek05a.uiexamples.ScrollToTopButton
import com.leehe228.eweek05a.viewmodel.ImageViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    imageViewModel: ImageViewModel = viewModel()
) {
    // val imageViewModel: ImageViewModel = viewModel() // 인라인 선언 가능
    val imageList = imageViewModel.imageList
    // var scrollState = rememberScrollState()
    val orientation = LocalConfiguration.current.orientation
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val showButton by remember {
        derivedStateOf {
            state.firstVisibleItemIndex > 0
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            LazyColumn(
                state = state,
                modifier = modifier,
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(items = imageList) { index, imageData ->
                    when (imageData.buttonType) {
                        ButtonType.ICON -> {
                            ImageWithButton(image = imageData.image) {
                                ButtonWithIcon(likes = imageData.likes) {
                                    imageList[index] = imageData.copy(likes = imageData.likes + 1)
                                }
                            }
                        }

                        ButtonType.BADGE -> {
                            ImageWithButton(image = imageData.image) {
                                ButtonWithBadge(likes = imageData.likes) {
                                    imageList[index] = imageData.copy(likes = imageData.likes + 1)
                                }
                            }
                        }

                        ButtonType.EMOJI -> {
                            ImageWithButton(image = imageData.image) {
                                ButtonWithEmoji(
                                    likes = imageData.likes,
                                    dislikes = imageData.dislikes,
                                    onClickLikes = {
                                        imageList[index] =
                                            imageData.copy(likes = imageData.likes + 1)
                                    },
                                    onClickDisLikes = {
                                        imageList[index] =
                                            imageData.copy(dislikes = imageData.dislikes + 1)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        } else {
            LazyRow(
                state = state,
                modifier = modifier,
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                itemsIndexed(items = imageList) { index, imageData ->
                    when (imageData.buttonType) {
                        ButtonType.ICON -> {
                            ImageWithButton(image = imageData.image) {
                                ButtonWithIcon(likes = imageData.likes) {
                                    imageList[index] = imageData.copy(likes = imageData.likes + 1)
                                }
                            }
                        }

                        ButtonType.BADGE -> {
                            ImageWithButton(image = imageData.image) {
                                ButtonWithBadge(likes = imageData.likes) {
                                    imageList[index] = imageData.copy(likes = imageData.likes + 1)
                                }
                            }
                        }

                        ButtonType.EMOJI -> {
                            ImageWithButton(image = imageData.image) {
                                ButtonWithEmoji(
                                    likes = imageData.likes,
                                    dislikes = imageData.dislikes,
                                    onClickLikes = {
                                        imageList[index] =
                                            imageData.copy(likes = imageData.likes + 1)
                                    },
                                    onClickDisLikes = {
                                        imageList[index] =
                                            imageData.copy(dislikes = imageData.dislikes + 1)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
        AnimatedVisibility(visible = showButton) {
            ScrollToTopButton {
                scope.launch {
                    state.scrollToItem(0)
                }
            }
        }
    }

    /* if (orientation == Configuration.ORIENTATION_PORTRAIT) {
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
    } */
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
