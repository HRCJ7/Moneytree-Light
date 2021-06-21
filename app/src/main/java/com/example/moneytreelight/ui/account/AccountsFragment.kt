package com.example.moneytreelight.ui.account

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneytreelight.R
import com.example.moneytreelight.data.model.account.Account
import com.example.moneytreelight.di.component.FragmentComponent
import com.example.moneytreelight.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_accounts.*

class AccountsFragment : BaseFragment<AccountsViewModel>(), TransactionListener {
    override fun provideLayoutId(): Int = R.layout.fragment_accounts

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
            total.text = "JPY$it"
        })

    }

    override fun setupView(view: View) {
        viewModel.fetchAccountData()
    }

    override fun onClickListener(account: Account) {
        val directions = AccountsFragmentDirections.actionAccountsFragmentToTransactionFragment(
            account.institution,
            account.name,
            account.currency,
            account.id
        )
        findNavController().navigate(directions)
    }

}