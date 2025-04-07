package com.leehe228.eweek05a.model

sealed class ImageUri { // sealed class: 이 파일 내부에서만 상속할 수 있음
    data class ResImage(val resID: Int): ImageUri()
    data class WebImage(val webUrl: String): ImageUri()
}
