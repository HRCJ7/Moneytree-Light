package com.example.moneytreelight.data.repository.account

import com.example.moneytreelight.data.model.account.AccountListModel
import io.reactivex.rxjava3.core.Single

/**
 * Using this repository class we can integrate remote API service in future
 */
class AccountRepository(private var accountDataService: AccountDataService) {

    fun fetchAccountData(): Single<AccountListModel> {
        return accountDataService.fetchAccountData()
    }
}