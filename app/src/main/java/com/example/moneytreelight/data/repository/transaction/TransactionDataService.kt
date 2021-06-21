package com.example.moneytreelight.data.repository.transaction

import com.example.moneytreelight.data.model.transaction.TransactionListModel
import io.reactivex.rxjava3.core.Single

interface TransactionDataService {
    fun fetchTransactionData(id: Int?) : Single<TransactionListModel>
}