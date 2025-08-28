package com.wp7367.videoplayer.ui_layer.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wp7367.videoplayer.ui_layer.navigation.VideosInFolderScreen // Import the new screen route
import com.wp7367.videoplayer.ui_layer.viewModel.MyViewModel

@Composable
fun FolderScreenUi(viewModel: MyViewModel = hiltViewModel(), navController: NavController) {

    val folderMapState = viewModel.videoMapByFolder.collectAsState()
    val folderNames = folderMapState.value.keys.toList()

    if (folderNames.isEmpty()) {
        Text(
            text = "No folders found.",
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp
        )
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(vertical = 7.dp)) {
            items(folderNames) { folderName ->

                Card(modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 16.dp)
                    .clickable {
                        navController.navigate(VideosInFolderScreen(folderName = folderName))
                    }
                    .height(70.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .fillParentMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(painter = painterResource(id = com.wp7367.videoplayer.R.drawable.folder333222),contentDescription = null,
                            modifier = Modifier.padding(10.dp).size(40.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = folderName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}
