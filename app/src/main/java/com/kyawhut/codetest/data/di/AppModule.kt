package com.kyawhut.codetest.data.di

import android.content.Context
import androidx.room.Room
import com.kyawhut.codetest.BuildConfig
import com.kyawhut.codetest.data.db.AppDatabase
import com.kyawhut.codetest.data.db.dao.MoviesDao
import com.kyawhut.codetest.data.network.API
import com.kyawhut.codetest.data.network.interceptors.ConnectionInterceptor
import com.kyawhut.codetest.data.network.interceptors.HeaderInterceptor
import com.kyawhut.codetest.utils.network.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * @author kyawhtut
 * @date 10/16/21
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "code-test-db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )
    }

    @Provides
    @Singleton
    fun provideAPI(
        interceptor: ConnectionInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor,
    ): API {
        return NetworkUtil.createService(
            API::class,
            BuildConfig.API_BASE_URL,
            listOf(headerInterceptor, interceptor, loggingInterceptor)
        )
    }

    @Provides
    @Singleton
    fun provideMoviesDao(appDatabase: AppDatabase): MoviesDao {
        return appDatabase.moviesDao
    }

}
