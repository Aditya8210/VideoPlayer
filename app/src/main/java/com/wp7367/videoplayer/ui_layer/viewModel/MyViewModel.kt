package com.wp7367.videoplayer.ui_layer.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wp7367.videoplayer.data_layer.VideoFile
import com.wp7367.videoplayer.domain_layer.VideoAppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(val application: Application,val repo: VideoAppRepo): ViewModel() {

    val isLoading = MutableStateFlow(false)
    // Changed VideoList to store a Map
    val videoMapByFolder = MutableStateFlow(emptyMap<String, List<VideoFile>>())
    
    // Kept original VideoList for all videos, if needed elsewhere
    val allVideosList = MutableStateFlow(emptyList<VideoFile>())

    fun loadAllVideos(){
        isLoading.value= true
        viewModelScope.launch {
            repo.getAllVideos(application=application).collectLatest{
                allVideosList.value = it
            }
        }
        isLoading.value = false
    }

    init {
        loadAllVideos() // Loads all videos into allVideosList
        loadVideosByFolder() // Loads videos grouped by folder into videoMapByFolder
    }

    // Renamed function for clarity and updated to populate the map
    fun loadVideosByFolder(){
        viewModelScope.launch {
            isLoading.value = true
            repo.getVideoByFolder(application).collectLatest {
                videoMapByFolder.value = it // Assign the map directly
            }
            isLoading.value = false
        }
    }
}
