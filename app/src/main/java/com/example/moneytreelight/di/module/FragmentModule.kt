package com.example.moneytreelight.di.module

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import com.example.moneytreelight.data.local.db.DatabaseService
import com.example.moneytreelight.data.local.file.LocalFileService
import com.example.moneytreelight.ui.account.AccountsViewModel
import com.example.moneytreelight.ui.base.BaseFragment
import com.example.moneytreelight.ui.transaction.TransactionViewModel
import com.example.moneytreelight.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideAccountsViewModel(
            localFileService: LocalFileService,
            databaseService: DatabaseService,
            sharedPreferences: SharedPreferences,
            compositeDisposable: CompositeDisposable
    ): AccountsViewModel = ViewModelProvider(
            fragment, ViewModelProviderFactory(AccountsViewModel::class) {
        AccountsViewModel(
                localFileService,
                databaseService,
                sharedPreferences,
                compositeDisposable
        )
    }).get(AccountsViewModel::class.java)

    @Provides
    fun provideTransactionViewModel(
            localFileService: LocalFileService,
            databaseService: DatabaseService,
            sharedPreferences: SharedPreferences,
            compositeDisposable: CompositeDisposable
    ): TransactionViewModel = ViewModelProvider(
            fragment, ViewModelProviderFactory(TransactionViewModel::class) {
        TransactionViewModel(
                localFileService,
                databaseService,
                sharedPreferences,
                compositeDisposable
        )
    }).get(TransactionViewModel::class.java)
}