package com.example.movieapp.utils.di

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.example.movieapp.model.data_source.remote.ApiClient
import com.example.movieapp.model.repository.MovieRepositoryImp
import com.example.movieapp.model.repository.MovieRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieRepository(apiClient: ApiClient): MovieRepository {
        return MovieRepositoryImp(apiClient)
    }
    @Singleton
    @Provides
    fun providesInputMethodManager(@ApplicationContext context: Context) =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    @Singleton
    @Provides
    fun provideCoroutineIO(): CoroutineScope =
        CoroutineScope(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideMainCoroutineDispatcher(): MainCoroutineDispatcher = Dispatchers.Main

}