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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.wp7367.videoplayer.ui_layer.navigation.PlayerScreen
import com.wp7367.videoplayer.ui_layer.navigation.VideosInFolderScreen
import com.wp7367.videoplayer.ui_layer.viewModel.MyViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File

@Composable
fun VideoScreenUi(viewModel: MyViewModel= hiltViewModel(),navController: NavController){

    val allVideos = viewModel.allVideosList.collectAsState()



    LazyColumn (modifier = Modifier.fillMaxSize().padding(vertical = 7.dp))


    {

        items(allVideos.value){
            Card (
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 16.dp)
                    .clickable {
                        navController.navigate(PlayerScreen(videoUri = it.path, videoTitle = it.title))
                    }
                    .height(70.dp)

            )
            {
                Row(modifier = Modifier
                        .fillParentMaxWidth()
                        .fillParentMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = it.thumbnail?.let { File(it) },
                            onError = { error ->
                                Log.e("CoilError", "Error loading image: ${it.path} (Thumbnail path: ${it.thumbnail})", error.result.throwable)
                            }
                        ),
                        contentDescription = "Video Thumbnail",
                        modifier = Modifier.size(width = 120.dp, height = 70.dp) // You can adjust the size
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = it.title.toString())
                    // You can add more video details here if needed
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }


}