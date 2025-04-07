package com.leehe228.eweek05a.model

import androidx.compose.foundation.text.input.TextFieldState.Saver.restore
import androidx.compose.runtime.saveable.listSaver

data class ImageData(
    val image: ImageUri,
    val buttonType: ButtonType = ButtonType.ICON,
    var likes: Int = 0,
    var dislikes: Int = 0
) {
    companion object {
        val ImageSaver = listSaver<ImageData, Any>(
            // ImageUri 타입을 리스트로 펼쳐서 저장
            save = { item -> // 매개변수 안적으면 it으로 받음
                listOf(
                    // 첫 번째 원소: 이미지 ID 또는 URL (클래스 타입에 따라)
                    when (item.image) { // ImageUri 타입
                        is ImageUri.ResImage -> item.image.resID // is -> 타입 식별 및 변환까지 해줌
                        is ImageUri.WebImage -> item.image.webUrl // 각 자식 클래스 타입으로 자동 변환됨
                    },

                    // 두 번째 원소: Button Type (Enum)
                    item.buttonType.name, // string type으로 변환해줌

                    // 3, 4번째 원소: 좋아요, 싫어요 수
                    item.likes, item.dislikes
                )
            },

            // 펼쳐진 리스트를 ImageUri 타입으로 변환해서 반환
            restore = { list ->
                val img = list[0]
                val image = when(img) {
                    is Int -> ImageUri.ResImage(img)
                    is String -> ImageUri.WebImage(img)
                    else -> throw IllegalArgumentException("Unexpected type error")
                }

                // 마지막 expression 반환됨
                ImageData(
                    image = image,
                    buttonType = ButtonType.valueOf(list[1] as String),
                    likes = list[2] as Int,
                    dislikes = list[3] as Int
                )
            }
        )
    }
}
