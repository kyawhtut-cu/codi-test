package com.kyawhut.codetest.data.di

import com.kyawhut.codetest.ui.detail.DetailRepository
import com.kyawhut.codetest.ui.detail.DetailRepositoryImpl
import com.kyawhut.codetest.ui.popular.PopularRepository
import com.kyawhut.codetest.ui.popular.PopularRepositoryImpl
import com.kyawhut.codetest.ui.upcoming.UpComingRepository
import com.kyawhut.codetest.ui.upcoming.UpComingRepositoryImpl
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
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUpComingRepository(repository: UpComingRepositoryImpl): UpComingRepository {
        return repository
    }

    @Provides
    @Singleton
    fun providePopularRepository(repository: PopularRepositoryImpl): PopularRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideDetailRepository(repository: DetailRepositoryImpl): DetailRepository {
        return repository
    }
}
