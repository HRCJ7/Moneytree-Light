package com.example.moneytreelight.di.component

import com.example.moneytreelight.di.module.ApplicationModule
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.moneytreelight.MoneytreeApplication
import com.example.moneytreelight.data.local.db.DatabaseService
import com.example.moneytreelight.data.local.file.LocalFileService
import com.example.moneytreelight.di.ApplicationContext
import dagger.Component
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: MoneytreeApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getCompositeDisposable(): CompositeDisposable

    fun getLocalFileService(): LocalFileService

    fun getDatabaseService(): DatabaseService

    fun getSharedPreferences(): SharedPreferences
}