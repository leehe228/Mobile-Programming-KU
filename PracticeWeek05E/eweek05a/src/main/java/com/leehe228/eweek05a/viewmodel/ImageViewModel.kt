package com.leehe228.eweek05a.viewmodel

import androidx.lifecycle.ViewModel
import com.leehe228.eweek05a.model.ImageData
import com.leehe228.eweek05a.model.ImageListFactory

class ImageViewModel: ViewModel() {
    private val _imageList = ImageListFactory.makeListImage()
    val imageList: MutableList<ImageData>
        get() = _imageList
}
