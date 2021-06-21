package com.example.moneytreelight.data.model.transaction

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransactionListModel (
        @Expose
        @SerializedName("transactions")
        val transactions: List<Transaction>
)