package com.example.moneytreelight.ui.account

import com.example.moneytreelight.data.local.db.entity.AccountEntity

/**
 * This listener is use to get on click callback when user click account list item
 */
interface TransactionListener {
    fun onClickListener(account: AccountEntity)
}