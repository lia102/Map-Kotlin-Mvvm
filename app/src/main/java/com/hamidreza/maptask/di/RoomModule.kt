package com.hamidreza.maptask.di

import android.content.Context
import androidx.room.Room
import com.hamidreza.maptask.data.local.MapDao
import com.hamidreza.maptask.data.local.MapDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {


    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): MapDataBase {
        return Room.databaseBuilder(
            context,
            MapDataBase::class.java,
            MapDataBase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(articleDataBase: MapDataBase): MapDao {
        return articleDataBase.getMapDao()
    }

}