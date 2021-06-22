package com.example.moneytreelight.ui.transaction

import com.example.moneytreelight.data.local.db.entity.TransactionEntity

data class TransactionGroup(
        val month: String,
        val transactionList: List<TransactionEntity>
)