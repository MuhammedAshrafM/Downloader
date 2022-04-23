package com.example.nagwaapp.di

import com.example.nagwaapp.data.AttachmentsDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideAttachmentsDataSource() = AttachmentsDataSourceImpl()

}