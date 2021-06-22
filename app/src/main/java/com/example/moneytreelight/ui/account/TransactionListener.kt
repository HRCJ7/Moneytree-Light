package com.example.moneytreelight.ui.account

import com.example.moneytreelight.data.local.db.entity.AccountEntity

interface TransactionListener {
    fun onClickListener(account: AccountEntity)
}