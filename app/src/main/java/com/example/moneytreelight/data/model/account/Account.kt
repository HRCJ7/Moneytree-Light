package com.example.moneytreelight.data.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Account(
    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("institution")
    val institution: String,

    @Expose
    @SerializedName("currency")
    val currency: String,

    @Expose
    @SerializedName("current_balance")
    val currentBalance: Float,

    @Expose
    @SerializedName("current_balance_in_base")
    val currentBalanceInBase: Float
)