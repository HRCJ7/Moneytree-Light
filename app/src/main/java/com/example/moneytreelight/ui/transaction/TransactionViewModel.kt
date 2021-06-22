package com.example.moneytreelight.ui.transaction

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.moneytreelight.data.local.db.DatabaseService
import com.example.moneytreelight.data.local.db.entity.TransactionEntity
import com.example.moneytreelight.data.local.file.LocalFileService
import com.example.moneytreelight.data.repository.transaction.TransactionRepository
import com.example.moneytreelight.ui.base.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TransactionViewModel(
        private var localFileService: LocalFileService,
        private var databaseService: DatabaseService,
        private var sharedPreferences: SharedPreferences,
        private var compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    val transactionGroups: MutableLiveData<List<TransactionGroup>> = MutableLiveData()
    val totalValue: MutableLiveData<Float> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {}

    private fun updateTransactionList(transactionList: List<TransactionEntity>): List<TransactionEntity> {
        val transactionEntityList = mutableListOf<TransactionEntity>()
        transactionList.forEach {
            transactionEntityList.add(TransactionEntity(it.id, it.account_id, it.category_id, it.date, it.description, it.amount, it.date.subSequence(0, 7) as String))
        }
        return transactionEntityList
    }

    private fun getTotalValue(list: List<TransactionEntity>): Float {
        var total = 0.0F
        list.forEach {
            total += it.amount
        }
        return total
    }

    fun fetchTransactionDataFromDatabase(id: Int?) {
        loading.postValue(true)
        compositeDisposable.addAll(
                id?.let {
                    databaseService.transactionDao().getAll(it)
                            .subscribeOn(Schedulers.io())
                            .subscribe({
                                loading.postValue(false)
                                var totalAmount = 0.0F
                                val transactionGroupList = mutableListOf<TransactionGroup>()
                                val transactionGroupMap = updateTransactionList(it).sortedByDescending { it.date }.groupBy { it.month }

                                transactionGroupMap.forEach {
                                    transactionGroupList.add(TransactionGroup(it.key, it.value))
                                    totalAmount += getTotalValue(it.value)
                                }
                                transactionGroups.postValue(transactionGroupList)
                                totalValue.postValue(totalAmount)
                            }, {
                                loading.postValue(false)
                            })
                }
        )
    }

    fun fetchTransactionDataFromFile(id: Int?) {
        loading.postValue(true)
        compositeDisposable.addAll(
                TransactionRepository(localFileService).fetchTransactionData(id)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    loading.postValue(false)
                                    val transactionDaoList: MutableList<TransactionEntity> =
                                            mutableListOf()
                                    it.transactions.forEach {
                                        transactionDaoList.add(
                                                TransactionEntity(
                                                        it.id,
                                                        it.account_id,
                                                        it.category_id,
                                                        it.date,
                                                        it.description,
                                                        it.amount,
                                                        it.date.subSequence(0, 7) as String
                                                )
                                        )
                                    }
                                    storeTransactionData(id, transactionDaoList)
                                },
                                {
                                    loading.postValue(false)
                                }

                        )

        )
    }

    private fun storeTransactionData(id: Int?, transactionList: List<TransactionEntity>) {
        loading.postValue(true)
        val editor = sharedPreferences.edit()
        compositeDisposable.addAll(
                databaseService.transactionDao().insert(transactionList)
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            loading.postValue(false)
                            editor.putBoolean("TRANSACTIONS_STORED", true)
                            editor.apply()
                            fetchTransactionDataFromDatabase(id)
                        }, {
                            loading.postValue(false)
                            editor.putBoolean("TRANSACTIONS_STORED", false)
                            editor.apply()
                            deleteTransactionTable()
                        })
        )
    }

    private fun deleteTransactionTable() {
        compositeDisposable.addAll(
                databaseService.transactionDao().deleteAll()
                        .subscribeOn(Schedulers.io())
                        .subscribe {}
        )
    }
}