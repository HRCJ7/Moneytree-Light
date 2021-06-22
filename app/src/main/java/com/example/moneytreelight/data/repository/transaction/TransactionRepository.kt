package com.example.moneytreelight.data.repository.transaction

import com.example.moneytreelight.data.model.transaction.TransactionListModel

import io.reactivex.rxjava3.core.Single

/**
 * Using this repository class we can integrate remote API service in future
 */
class TransactionRepository(var transactionDataService: TransactionDataService) {
    fun fetchTransactionData(id: Int?): Single<TransactionListModel> {
        return transactionDataService.fetchTransactionData(id)
    }
}