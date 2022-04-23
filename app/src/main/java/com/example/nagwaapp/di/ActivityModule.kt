package com.example.nagwaapp.di

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import com.example.nagwaapp.R
import com.example.nagwaapp.utils.LoadingProgressBarDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    @ActivityScoped
    fun provideLoadingProgressBarDialog(activity: Activity): LoadingProgressBarDialog {
        return LoadingProgressBarDialog(activity)
    }

}