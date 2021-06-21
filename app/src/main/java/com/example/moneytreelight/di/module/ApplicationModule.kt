package com.example.moneytreelight.di.module

import android.app.Application
import android.content.Context
import com.example.moneytreelight.MoneytreeApplication
import com.example.moneytreelight.data.local.file.LocalFileService
import com.example.moneytreelight.di.ApplicationContext
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MoneytreeApplication) {
    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideLocalFileService(): LocalFileService = LocalFileService(application)

}