package com.wp7367.videoplayer.ui_layer.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.wp7367.videoplayer.ui_layer.navigation.PlayerScreen
import com.wp7367.videoplayer.ui_layer.viewModel.MyViewModel
import java.io.File

@Composable
fun VideosInFolderScreen(navController: NavController, folderName: String, viewModel: MyViewModel = hiltViewModel())
{

//  ~ Here State is Collect ~

    val videosInFolder = viewModel.videoMapByFolder.collectAsState().value[folderName]



    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Videos in: $folderName",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        if (videosInFolder.isNullOrEmpty()) {
            Text(text = "No videos found in this folder.")
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(videosInFolder) { videoFile ->
                    Card(
                        onClick = {
                            navController.navigate(PlayerScreen(videoUri = videoFile.path, videoTitle = videoFile.title))
                        },
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = videoFile.thumbnail?.let { File(it) },
                                    onError = { error ->
                                        Log.e("CoilError", "Error loading image: ${videoFile.path} (Thumbnail path: ${videoFile.thumbnail})", error.result.throwable)
                                    }
                                ),
                                contentDescription = "Video Thumbnail",
                                modifier = Modifier.size(width = 120.dp, height = 70.dp) // You can adjust the size
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = videoFile.title.toString())
                            // You can add more video details here if needed
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}
