package com.wp7367.videoplayer.ui_layer.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable

fun ExoPlayerScreenUi(

    videoUri: String,
){

    val context = LocalContext.current

    val exoPlayer = remember {

        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
            playWhenReady = true
            play()
        }
    }
    Column (modifier = Modifier.fillMaxSize()
        .clickable {

             if (exoPlayer.isPlaying) {
                 exoPlayer.pause()
             } else {
                 exoPlayer.play()
             }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement =Arrangement.Center)
    {
        DisposableEffect(
            AndroidView(
                factory = {
                    PlayerView(context).apply{
                        player = exoPlayer
                    }

                },
                update = {
                    it.player=exoPlayer
                }
            )
        ) {
            onDispose { exoPlayer.release() }
        }
    }
}