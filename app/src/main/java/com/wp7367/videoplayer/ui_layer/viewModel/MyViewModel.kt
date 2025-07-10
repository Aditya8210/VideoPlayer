package com.wp7367.videoplayer.ui_layer.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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
    val VideoList = MutableStateFlow(emptyList<VideoFile>())
  fun loadAllVideos(){
      isLoading.value= true
      viewModelScope.launch {
          repo.getAllVideos(application=application).collectLatest{
              VideoList.value = it
          }

      }
      isLoading.value = false
  }
    init {
        loadAllVideos()
    }
}

