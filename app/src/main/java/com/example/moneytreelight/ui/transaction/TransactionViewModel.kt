package com.example.moneytreelight.ui.transaction

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moneytreelight.data.local.file.LocalFileService
import com.example.moneytreelight.data.model.transaction.NewTransaction
import com.example.moneytreelight.data.model.transaction.Transaction
import com.example.moneytreelight.data.repository.transaction.TransactionRepository
import com.example.moneytreelight.ui.base.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TransactionViewModel(
        var localFileService: LocalFileService,
        var compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    val transactionGroups: MutableLiveData<List<TransactionGroup>> = MutableLiveData()
    val totalValue: MutableLiveData<Float> = MutableLiveData()

    override fun onCreate() {}

    fun fetchTransactionData(id: Int?) {
        compositeDisposable.addAll(
                TransactionRepository(localFileService).fetchTransactionData(id)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    var totalAmount = 0.0F
                                    val transactionGroupList = mutableListOf<TransactionGroup>()
                                    val transactionGroupMap = updateTransactionList(it.transactions).sortedByDescending {it.date}.groupBy { it.month }

                                    transactionGroupMap.forEach {
                                        transactionGroupList.add(TransactionGroup(it.key, it.value))
                                        totalAmount += getTotalValue(it.value)
                                    }
                                    transactionGroups.postValue(transactionGroupList)
                                    totalValue.postValue(totalAmount)
                                },
                                {
                                    Log.d("Check", "Fail")
                                }

                        )

        )
    }

    fun updateTransactionList(transactionList: List<Transaction>): List<NewTransaction> {
        val newTransactionList = mutableListOf<NewTransaction>()
        transactionList.forEach {
            newTransactionList.add(NewTransaction(it.id, it.account_id, it.category_id, it.date, it.description, it.amount, it.date.subSequence(0, 7) as String))
        }
        return newTransactionList
    }

    fun getTotalValue(list: List<NewTransaction>) : Float {
        var total = 0.0F
        list.forEach {
            total += it.amount
        }
        return total
    }

}