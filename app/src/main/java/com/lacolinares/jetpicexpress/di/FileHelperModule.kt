package com.lacolinares.jetpicexpress.di

import android.content.Context
import com.lacolinares.jetpicexpress.util.FileHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class FileHelperModule {

    @Provides
    fun provideFileHelper(
        @ApplicationContext context: Context
    ): FileHelper{
        return FileHelper(context)
    }
}