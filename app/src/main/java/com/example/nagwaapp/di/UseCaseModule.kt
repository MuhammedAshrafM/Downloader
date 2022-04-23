package com.example.nagwaapp.di

import com.example.nagwaapp.data.AttachmentsDataSourceImpl
import com.example.nagwaapp.domain.GetAttachmentsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAttachmentsUseCase(attachmentsDataSource: AttachmentsDataSourceImpl) =
        GetAttachmentsUseCase(attachmentsDataSource)

}