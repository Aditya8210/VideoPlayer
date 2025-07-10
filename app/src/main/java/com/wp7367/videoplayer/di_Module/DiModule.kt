package com.wp7367.videoplayer.di_Module

import com.wp7367.videoplayer.data_layer.VideoAppRepoImp
import com.wp7367.videoplayer.domain_layer.VideoAppRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DataModel{

    @Provides

    fun provideRepo(): VideoAppRepo{
        return VideoAppRepoImp()

    }
}