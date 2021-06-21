package com.example.moneytreelight.data.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccountListModel(
        @Expose
        @SerializedName("accounts")
        val accounts: List<Account>
)