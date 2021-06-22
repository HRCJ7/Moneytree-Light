package com.example.moneytreelight.data.local.file

import android.content.Context
import com.example.moneytreelight.data.model.account.AccountListModel
import com.example.moneytreelight.data.model.transaction.TransactionListModel
import com.example.moneytreelight.data.repository.account.AccountDataService
import com.example.moneytreelight.data.repository.transaction.TransactionDataService
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Single

class LocalFileService(private val context: Context) : AccountDataService, TransactionDataService {
    private val gson = Gson()

    /**
     * This method fetch account data from accounts.json file
     * @return Single<AccountListModel> return Single Observable
     */
    override fun fetchAccountData(): Single<AccountListModel> {
        val json: String =
            context.assets.open("accounts.json").bufferedReader().use { it.readText() }
        val accountList = gson.fromJson(json, AccountListModel::class.java)
        return Single.just(accountList)
    }

    /**
     * This method fetch transaction data from the given files.This select each file according to the id number.
     * @param id selected account id
     * @return Single<TransactionListModel> return Single Observable
     */
    override fun fetchTransactionData(id: Int?): Single<TransactionListModel> {
        var fileName = "transactions_3.json"

        when (id) {
            1 -> fileName = "transactions_1.json"
            2 -> fileName = "transactions_2.json"
            3 -> fileName = "transactions_3.json"
        }

        val json: String =
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        val transactionList = gson.fromJson(json, TransactionListModel::class.java)
        return Single.just(transactionList)
    }
}