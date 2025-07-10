package com.wp7367.videoplayer.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.wp7367.videoplayer.ui_layer.screen.ExoPlayerScreenUi
import com.wp7367.videoplayer.ui_layer.screen.HomeScreenUi

@Composable
fun  AppNavigation (modifier: Modifier = Modifier){

val navController = rememberNavController()
    NavHost(navController=navController, startDestination = HomeScreen) {

        composable<HomeScreen>{
            HomeScreenUi(navController)
        }

        composable<PlayerScreen>{
            val videoUri: PlayerScreen = it.toRoute<PlayerScreen>()
            ExoPlayerScreenUi(videoUri = videoUri.videoUri)

        }
    }
}