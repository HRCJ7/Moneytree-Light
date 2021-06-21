package com.example.moneytreelight.ui.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moneytreelight.data.local.file.LocalFileService
import com.example.moneytreelight.data.repository.account.AccountRepository
import com.example.moneytreelight.ui.base.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AccountsViewModel(
    var localFileService: LocalFileService,
    var compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    val accountGroups: MutableLiveData<List<AccountGroup>> = MutableLiveData()
    val total: MutableLiveData<Float> = MutableLiveData()

    override fun onCreate() {
    }

    fun fetchAccountData() {
        compositeDisposable.addAll(
            AccountRepository(localFileService).fetchAccountData()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        val accountGroupMap =
                            it.accounts.sortedBy { it.name }.groupBy { it.institution }
                        val accountGroupList = mutableListOf<AccountGroup>()
                        accountGroupMap.forEach {
                            accountGroupList.add(AccountGroup(it.key, it.value))
                        }
                        accountGroups.postValue(accountGroupList)

                        var totalAmount: Float = 0.0F
                        it.accounts.forEach {
                            totalAmount += it.currentBalanceInBase
                        }
                        total.postValue(totalAmount)
                    },
                    {
                        Log.d("Check", "Fail")
                    }

                )

        )
    }
}
