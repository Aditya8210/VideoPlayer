package com.wp7367.videoplayer.data_layer

import android.app.Application
import android.provider.MediaStore
import com.wp7367.videoplayer.domain_layer.VideoAppRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.io.File

class VideoAppRepoImp: VideoAppRepo {
    override suspend fun getAllVideos(application: Application): Flow<List<VideoFile>> {
        val allVideos = ArrayList<VideoFile>()

        val Projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DISPLAY_NAME,
        )

        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val cursor = application.contentResolver.query(uri,Projection,null,null,null)
        if (cursor!=null){
            while (cursor.moveToNext()){
                val id = cursor.getString(0)
                val path = cursor.getString(1)
                val title = cursor.getString(2)
                val size = cursor.getString(3)
                val duration = cursor.getString(4)
                val thumbnail = cursor.getString(5)

                val videoFile = VideoFile(
                    id = id,
                    path= path,
                    title=title,
                    size=size,
                    duration=duration,
                    thumbnail=thumbnail
                )
                allVideos.add(videoFile)
                if (cursor.isLast){
                    break
                }

            }
        }
        cursor?.close()
        return flow {
            emit(allVideos)
        }
    }

    override suspend fun getVideoByFolder(application: Application): Flow<Map<String, List<VideoFile>>> {
        val allVideos = getAllVideos(application).first()
        val videoByFolder = allVideos.groupBy {
            File(it.path).parentFile.name ?: "unknown"
        }
        return flow { emit(videoByFolder) }
    }
}