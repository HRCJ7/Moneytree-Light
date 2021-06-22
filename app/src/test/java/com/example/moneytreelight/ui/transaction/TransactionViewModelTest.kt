package com.example.moneytreelight.ui.transaction

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moneytreelight.data.local.db.DatabaseService
import com.example.moneytreelight.data.local.file.LocalFileService
import com.example.moneytreelight.data.model.transaction.TransactionListModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TransactionViewModelTest {
    @get: Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var localFileService: LocalFileService
    private lateinit var databaseService: DatabaseService
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var viewModel: TransactionViewModel

    @Before
    fun setup() {
        localFileService = mock()
        databaseService = mock()
        sharedPreferences = mock()
        compositeDisposable = mock()
        viewModel = TransactionViewModel(
            localFileService,
            databaseService,
            sharedPreferences,
            compositeDisposable
        )
    }

    @Before
    fun setupSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker({ it.run() }, true, true)
            }
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

    @After
    fun tearDown() {
        Mockito.reset(localFileService)
        Mockito.reset(databaseService)
        Mockito.reset(sharedPreferences)
        Mockito.reset(compositeDisposable)
    }

    @Test
    fun test_fetchTransactionDataFromFile() {
        val model = TransactionListModel(emptyList())
        val testSingle: Single<TransactionListModel> = Single.just(model)
        Mockito.`when`(localFileService.fetchTransactionData(1)).thenReturn(testSingle)
        viewModel.fetchTransactionDataFromFile(1)
        verify(localFileService).fetchTransactionData(1)
    }

}