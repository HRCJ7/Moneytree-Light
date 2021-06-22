package com.example.moneytreelight.ui.account

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.moneytreelight.data.local.db.DatabaseService
import com.example.moneytreelight.data.local.db.entity.AccountEntity
import com.example.moneytreelight.data.local.file.LocalFileService
import com.example.moneytreelight.data.repository.account.AccountRepository
import com.example.moneytreelight.ui.base.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AccountsViewModel(
    private var localFileService: LocalFileService,
    private var databaseService: DatabaseService,
    private var sharedPreferences: SharedPreferences,
    private var compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    val accountGroups: MutableLiveData<List<AccountGroup>> = MutableLiveData()
    val total: MutableLiveData<Float> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {
    }

    /**
     * This method use to fetch account data from local file.
     */
    fun fetchAccountDataFromFile() {
        loading.postValue(true)
        compositeDisposable.addAll(
            AccountRepository(localFileService).fetchAccountData()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { it ->
                        // From json file data is being created a AccountEntity object list which need to
                        // store in local database
                        val accountDaoList: MutableList<AccountEntity> =
                            mutableListOf()
                        it.accounts.forEach {
                            accountDaoList.add(
                                AccountEntity(
                                    it.id,
                                    it.name,
                                    it.institution,
                                    it.currency,
                                    it.currentBalance,
                                    it.currentBalanceInBase
                                )
                            )
                        }
                        loading.postValue(false)
                        storeAccountData(accountDaoList)
                    },
                    {
                        loading.postValue(false)
                    }

                )

        )
    }

    /**
     * This method use to store data in local database
     */
    private fun storeAccountData(accountList: List<AccountEntity>) {
        loading.postValue(true)
        compositeDisposable.addAll(
            databaseService.accountDao().insert(accountList)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        loading.postValue(false)
                        // once get success call back will update shared preference value
                        val editor = sharedPreferences.edit()
                        editor.putBoolean("ACCOUNTS_STORED", true)
                        editor.apply()
                        fetchAccountDataFromDatabase()
                    }, {
                        loading.postValue(false)
                    }
                )
        )
    }

    /**
     * This method use to fetch data from local database
     */
    fun fetchAccountDataFromDatabase() {
        loading.postValue(true)
        compositeDisposable.addAll(
            databaseService.accountDao().getAll()
                .subscribeOn(Schedulers.io())
                .subscribe({ it ->
                    loading.postValue(false)
                    val accountGroupMap =
                        it.sortedBy { it.name }.groupBy { it.institution }
                    val accountGroupList = mutableListOf<AccountGroup>()
                    accountGroupMap.forEach {
                        accountGroupList.add(AccountGroup(it.key, it.value))
                    }
                    accountGroups.postValue(accountGroupList)

                    var totalAmount = 0.0F
                    it.forEach {
                        totalAmount += it.currentBalanceInBase
                    }
                    total.postValue(totalAmount)
                }, {
                    loading.postValue(false)
                })
        )
    }
}
