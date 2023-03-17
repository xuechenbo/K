package com.xc.kotlindemo.model


data class MainModel(
    var name: String,
    var age: Int,
    var time: String,
    val action: (CameraAction) -> Unit
)

enum class CameraAction {
    StartPreview, StopPreview, Captured, Recorded
}

