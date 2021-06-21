package com.example.moneytreelight.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneytreelight.data.local.file.LocalFileService
import com.example.moneytreelight.ui.base.BaseActivity
import com.example.moneytreelight.ui.main.MainViewModel
import com.example.moneytreelight.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideMainViewModel(
        compositeDisposable: CompositeDisposable
    ): MainViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(compositeDisposable)
        }).get(MainViewModel::class.java)

}