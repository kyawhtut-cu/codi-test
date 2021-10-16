package com.kyawhut.codetest.data.di

import com.kyawhut.codetest.data.db.AppDatabase
import com.kyawhut.codetest.data.db.dao.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMoviesDao(appDatabase: AppDatabase): MoviesDao {
        return appDatabase.moviesDao
    }

}
