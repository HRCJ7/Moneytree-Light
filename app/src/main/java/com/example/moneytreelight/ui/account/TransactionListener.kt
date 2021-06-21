package com.example.moneytreelight.ui.account

import com.example.moneytreelight.data.model.account.Account

interface TransactionListener {
    fun onClickListener(account: Account)
}