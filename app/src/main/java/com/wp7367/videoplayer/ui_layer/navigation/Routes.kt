package com.wp7367.videoplayer.ui_layer.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object FolderScreen

@Serializable
data class PlayerScreen(val videoUri: String, val videoTitle: String?=null)

@Serializable
data class VideosInFolderScreen(val folderName: String)
