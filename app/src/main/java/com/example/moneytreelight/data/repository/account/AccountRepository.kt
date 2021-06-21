package com.example.moneytreelight.data.repository.account

import com.example.moneytreelight.data.model.account.AccountListModel
import com.example.moneytreelight.data.repository.account.AccountDataService
import io.reactivex.rxjava3.core.Single

class AccountRepository(var accountDataService: AccountDataService) {

    fun fetchAccountData() : Single<AccountListModel> {
       return accountDataService.fetchAccountData()
    }
}