package com.example.moneytreelight.ui.account

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneytreelight.R
import com.example.moneytreelight.data.local.db.entity.AccountEntity
import com.example.moneytreelight.di.component.FragmentComponent
import com.example.moneytreelight.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_accounts.*
import javax.inject.Inject

class AccountsFragment : BaseFragment<AccountsViewModel>(), TransactionListener {
    override fun provideLayoutId(): Int = R.layout.fragment_accounts

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun setupObservers() {

        viewModel.accountGroups.observe(this, Observer {
            rv_parent.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = AccountsGroupAdapter(it, context, this@AccountsFragment)
            }
        })

        viewModel.total.observe(this, Observer {
            tv_total.text = "JPY$it"
        })

        viewModel.loading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

    }

    override fun setupView(view: View) {
        // Here check data is stored or not.According to that data fetching method get decided.
        // Two types of data source is available 1.File 2.Local database
        if (sharedPreferences.getBoolean("ACCOUNTS_STORED", false)) {
            viewModel.fetchAccountDataFromDatabase()
        } else {
            viewModel.fetchAccountDataFromFile()
        }
    }

    override fun onClickListener(account: AccountEntity) {
        val directions = AccountsFragmentDirections.actionAccountsFragmentToTransactionFragment(
                account.institution,
                account.name,
                account.currency,
                account.id
        )
        findNavController().navigate(directions)
    }

}