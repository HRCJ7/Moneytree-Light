package com.example.moneytreelight.data.model.transaction

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Transaction(
        @Expose
        @SerializedName("id")
        val id: Int,

        @Expose
        @SerializedName("account_id")
        val account_id: Int,

        @Expose
        @SerializedName("category_id")
        val category_id: Int,


        @Expose
        @SerializedName("date")
        val date: String,

        @Expose
        @SerializedName("description")
        val description: String,

        @Expose
        @SerializedName("amount")
        val amount: Float
)