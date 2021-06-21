package com.example.moneytreelight.ui.account

import com.example.moneytreelight.data.model.account.Account

data class AccountGroup (
    val institution: String,
    val accountList: List<Account>
)