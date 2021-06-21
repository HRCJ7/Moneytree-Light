package com.example.moneytreelight.ui.transaction

import com.example.moneytreelight.data.model.transaction.NewTransaction

data class TransactionGroup(
        val month: String,
        val transactionList: List<NewTransaction>
)