### Directory Structure
```
Assignment2/app/src/main/java/com.leehe228.assignment2/
├── .DS_Store
├── MainActivity.kt
├── model/
│   └── ImageData.kt
├── ui/
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
└── uicomponents/
    ├── CheckBoxView.kt
    ├── ImageView.kt
    └── MainScreen.kt
```

### Core Logic
**Custom List Saver for SnapshotStateList**
```kotlin
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
```

**Composable ImageView containing checkboxes**
```kotlin
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
```

**rememberSaveable with saver**
```kotlin
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
```

### Video
<video src="https://github.com/user-attachments/assets/bf893643-9601-4ac6-8f7f-16e4c643e555" alt="">

