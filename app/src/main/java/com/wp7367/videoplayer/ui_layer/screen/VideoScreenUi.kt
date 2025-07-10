package com.wp7367.videoplayer.ui_layer.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wp7367.videoplayer.ui_layer.navigation.PlayerScreen
import com.wp7367.videoplayer.ui_layer.viewModel.MyViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun VideoScreenUi(viewModel: MyViewModel= hiltViewModel(),navController: NavController){

    val allVideos = viewModel.VideoList.collectAsState().value

    LazyColumn (modifier = Modifier.fillMaxSize())


    {

        items(allVideos){
            Card (
                onClick = {
                   navController.navigate(PlayerScreen(videoUri = it.path,it.title))
                }
            )
            {
                Column {
                    Text(text=it.title.toString())
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}