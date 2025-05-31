package com.wp7367.videoplayer.data_layer

data class VideoFile(

    val id: String?,
    var path : String,
    var title:String?,
    var size : String?,
    var duration:String?,
    var thumbnail:String?=null,
)
