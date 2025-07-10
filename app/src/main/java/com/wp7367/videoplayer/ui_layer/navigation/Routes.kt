package com.wp7367.videoplayer.ui_layer.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class PlayerScreen(val videoUri: String, val videoTitle: String?=null)