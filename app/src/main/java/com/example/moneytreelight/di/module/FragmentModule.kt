package com.example.moneytreelight.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneytreelight.data.local.file.LocalFileService
import com.example.moneytreelight.ui.account.AccountsViewModel
import com.example.moneytreelight.ui.base.BaseFragment
import com.example.moneytreelight.ui.transaction.TransactionFragment
import com.example.moneytreelight.ui.transaction.TransactionViewModel
import com.example.moneytreelight.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.io.File

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideAccountsViewModel(
        localFileService: LocalFileService,
        compositeDisposable: CompositeDisposable
    ): AccountsViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(AccountsViewModel::class) {
            AccountsViewModel(
                localFileService,
                compositeDisposable
            )
        }).get(AccountsViewModel::class.java)

    @Provides
    fun provideTransactionViewModel(
            localFileService: LocalFileService,
            compositeDisposable: CompositeDisposable
    ): TransactionViewModel = ViewModelProvider(
            fragment, ViewModelProviderFactory(TransactionViewModel::class) {
        TransactionViewModel(
                localFileService,
                compositeDisposable
        )
    }).get(TransactionViewModel::class.java)
}