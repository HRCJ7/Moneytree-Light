package com.example.moneytreelight.ui.transaction

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneytreelight.R
import com.example.moneytreelight.di.component.FragmentComponent
import com.example.moneytreelight.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_transaction.*
import kotlinx.android.synthetic.main.fragment_transaction.view.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TransactionFragment : BaseFragment<TransactionViewModel>() {

    var name: String? = null
    var currency: String? = null

    override fun provideLayoutId(): Int = R.layout.fragment_transaction

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun setupObservers() {

        viewModel.transactionGroups.observe(this, Observer {
            rv_transaction.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TransactionGroupAdapter(it, context, currency)
            }
        })

        viewModel.totalValue.observe(this, Observer {
            total.text = currency + it.toString()
        })
    }

    override fun setupView(view: View) {
        view.name.text = name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arg = arguments?.let { TransactionFragmentArgs.fromBundle(it) }
        viewModel.fetchTransactionData(arg?.id)
        name = arg?.name
        currency = arg?.currency
    }

}