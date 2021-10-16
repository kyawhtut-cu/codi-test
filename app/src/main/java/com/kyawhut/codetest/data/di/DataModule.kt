package com.kyawhut.codetest.data.di

import com.kyawhut.codetest.data.db.datasource.MoviesDataSource
import com.kyawhut.codetest.data.db.datasourceImpl.MoviesDataSourceImpl
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
    fun provideMovieSource(source: MoviesDataSourceImpl): MoviesDataSource {
        return source
    }

}
