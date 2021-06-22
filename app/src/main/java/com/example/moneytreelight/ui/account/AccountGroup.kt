package com.example.moneytreelight.ui.account

import com.example.moneytreelight.data.local.db.entity.AccountEntity

data class AccountGroup (
    val institution: String,
    val accountList: List<AccountEntity>
)