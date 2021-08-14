package com.lacolinares.jetpicexpress.di

import android.content.Context
import com.lacolinares.jetpicexpress.presentation.ui.editimage.mapper.EditImageMapper
import com.lacolinares.jetpicexpress.presentation.ui.editimage.mapper.EditImageMapperImpl
import com.lacolinares.jetpicexpress.presentation.ui.editimage.repository.EditImageRepository
import com.lacolinares.jetpicexpress.presentation.ui.editimage.repository.EditImageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EditImageRepositoryModule {

    @Provides
    @Singleton
    fun provideEditImageMapper(): EditImageMapper{
        return EditImageMapperImpl()
    }

    @Provides
    @Singleton
    fun provideEditImageRepository(
        @ApplicationContext context: Context,
        mapper: EditImageMapper,
    ) : EditImageRepository{
        return EditImageRepositoryImpl(context, mapper)
    }

}