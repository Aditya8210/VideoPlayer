package com.wp7367.videoplayer.domain_layer

import android.app.Application
import com.wp7367.videoplayer.data_layer.VideoFile
import kotlinx.coroutines.flow.Flow

interface VideoAppRepo {

    suspend fun getAllVideos(application: Application): Flow<List<VideoFile>>

    suspend fun getVideoByFolder(application: Application): Flow<Map<String,List<VideoFile>>>
}