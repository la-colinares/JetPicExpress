package com.lacolinares.jetpicexpress.di

import android.content.Context
import com.lacolinares.jetpicexpress.presentation.ui.viewimages.repository.ViewImagesRepository
import com.lacolinares.jetpicexpress.presentation.ui.viewimages.repository.ViewImagesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewImagesRepositoryModule {

    @Provides
    @Singleton
    fun provideViewImagesRepository(
        @ApplicationContext context: Context,
    ): ViewImagesRepository {
        return ViewImagesRepositoryImpl(context)
    }

}